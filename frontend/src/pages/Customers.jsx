import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { getAllCustomers } from '../api/customerApi'
import { markDelivery } from '../api/deliveryApi'
import CustomerForm from '../components/CustomerForm'
import Toast from '../components/Toast'
import '../styles/Customers.css'

function Customers() {
  const [customers, setCustomers] = useState([])
  const [loading, setLoading] = useState(true)

  const [toast, setToast] = useState({ visible: false, message: '', type: 'success' })

  useEffect(() => {
    fetchCustomers()
  }, [])

  const fetchCustomers = () => {
    getAllCustomers()
      .then((response) => {
        setCustomers(response.data)
        setLoading(false)
      })
      .catch((error) => {
        console.error('Error fetching customers:', error)
        setLoading(false)
      })
  }

  const getTodayDate = () => {
    const today = new Date()
    const year = today.getFullYear()
    const month = String(today.getMonth() + 1).padStart(2, '0')
    const day = String(today.getDate()).padStart(2, '0')
    return `${year}-${month}-${day}`
  }

  const showToast = (message, type) => {
    setToast({ visible: true, message, type })

    // Automatically hide the toast after 2.5 seconds
    setTimeout(() => {
      setToast((prev) => ({ ...prev, visible: false }))
    }, 2500)
  }

  const handleMarkDelivery = (customerId, delivered) => {
    const today = getTodayDate()

    markDelivery(customerId, today, delivered)
      .then(() => {
        showToast(
          delivered ? 'Marked as Delivered' : 'Marked as Skipped',
          delivered ? 'success' : 'error'
        )
      })
      .catch((error) => {
        console.error('Error marking delivery:', error)
        showToast('Something went wrong', 'error')
      })
  }

  return (
    <div>
      <div className="page-header">
        <div>
          <h2>Customers</h2>
          <p className="page-subtitle">{customers.length} total customers</p>
        </div>
      </div>

      <CustomerForm onCustomerAdded={fetchCustomers} />

      {loading ? (
        <p>Loading customers...</p>
      ) : customers.length === 0 ? (
        <div className="empty-state">
          <p>No customers added yet. Add your first customer above.</p>
        </div>
      ) : (
        <ul className="customer-list">
          {customers.map((customer) => (
            <li key={customer.id} className="customer-card">
              <div className="info">
                <Link className='links' to={`/customers/${customer.id}`}>
                  <strong>{customer.name}</strong>
                </Link>
                <p>{customer.address}</p>
              </div>
              <div className="details">
                {customer.dailyQty} L/day @ ₹{customer.rate}/L
              </div>
              <div className="delivery-actions">
                <button className="btn-delivered" onClick={() => handleMarkDelivery(customer.id, true)}>
                  Delivered 
                </button>
                <button className="btn-skipped" onClick={() => handleMarkDelivery(customer.id, false)}>
                  Skipped 
                </button>
              </div>
            </li>
          ))}
        </ul>
      )}

      <Toast message={toast.message} type={toast.type} visible={toast.visible} />
    </div>
  )
}

export default Customers