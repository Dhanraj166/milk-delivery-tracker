import { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import { getCustomerById } from '../api/customerApi'
import { getLogsForCustomer, getMonthlyBill } from '../api/deliveryApi'
import '../styles/CustomerDetails.css'

function CustomerDetails() {
  const { id } = useParams()

  const [customer, setCustomer] = useState(null)
  const [logs, setLogs] = useState([])
  const [bill, setBill] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    fetchCustomerData()
    console.log(customer)
    console.log(logs)
    console.log(bill)
    console.log(loading)
  }, [id])

  const fetchCustomerData = () => {
    getCustomerById(id)
      .then((response) => {
        setCustomer(response.data)
      })
      .catch((error) => {
        console.error('Error fetching customer:', error)
      })

    getLogsForCustomer(id)
      .then((response) => {
        setLogs(response.data)
        setLoading(false)
      })
      .catch((error) => {
        console.error('Error fetching logs:', error)
        setLoading(false)
      })

    const now = new Date()
    getMonthlyBill(id, now.getFullYear(), now.getMonth() + 1)
      .then((response) => {
        setBill(response.data)
      })
      .catch((error) => {
        console.error('Error fetching bill:', error)
      })
  }

  if (loading) {
    return <p>Loading customer details...</p>
  }

  if (!customer) {
    return <p>Customer not found.</p>
  }

  return (
    <div>
      <div className="customer-header">
        <h2>{customer.name}</h2>
        <p>{customer.address}</p>
        <p>{customer.dailyQty} L/day @ ₹{customer.rate}/L</p>
      </div>

      <h3 className="section-title">This Month's Bill</h3>
      {bill ? (
        <div className="bill-card">
          <div className="bill-item">
            <span className="bill-label">Delivered Days</span>
            <span className="bill-value">{bill.deliveredDays}</span>
          </div>
          <div className="bill-item">
            <span className="bill-label">Total Liters</span>
            <span className="bill-value">{bill.totalLiters}</span>
          </div>
          <div className="bill-item">
            <span className="bill-label">Total Amount</span>
            <span className="bill-value">₹{bill.totalAmount}</span>
          </div>
        </div>
      ) : (
        <p>Loading bill...</p>
      )}

      <h3 className="section-title">Delivery History</h3>
      {logs.length === 0 ? (
        <p>No delivery records yet.</p>
      ) : (
        <ul className="history-list">
          {logs.map((log) => (
            <li key={log.id} className="history-item">
              <span>{log.date}</span>
              <span className={log.delivered ? 'status-delivered' : 'status-skipped'}>
                {log.delivered ? 'Delivered' : 'Skipped'}
              </span>
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default CustomerDetails