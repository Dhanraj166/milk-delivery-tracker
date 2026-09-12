import { useState } from 'react'
import { addCustomer } from '../api/customerApi'
import '../styles/CustomerForm.css'

function CustomerForm({ onCustomerAdded }) {
  const [name, setName] = useState('')
  const [address, setAddress] = useState('')
  const [dailyQty, setDailyQty] = useState('')
  const [rate, setRate] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault()

    const newCustomer = {
      name: name,
      address: address,
      dailyQty: parseFloat(dailyQty),
      rate: parseFloat(rate)
    }

    addCustomer(newCustomer)
      .then((response) => {
        setName('')
        setAddress('')
        setDailyQty('')
        setRate('')
        onCustomerAdded()
      })
      .catch((error) => {
        console.error('Error adding customer:', error)
      })
  }

  return (
    <form className="customer-form" onSubmit={handleSubmit}>
      <h3>Add New Customer</h3>

      <div className="form-group">
        <label>Name</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>

      <div className="form-group">
        <label>Address</label>
        <input
          type="text"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          required
        />
      </div>

      <div className="form-group">
        <label>Daily Quantity (Liters)</label>
        <input
          type="number"
          step="0.1"
          value={dailyQty}
          onChange={(e) => setDailyQty(e.target.value)}
          required
        />
      </div>

      <div className="form-group">
        <label>Rate (₹ per Liter)</label>
        <input
          type="number"
          step="0.1"
          value={rate}
          onChange={(e) => setRate(e.target.value)}
          required
        />
      </div>

      <button className="submit-btn" type="submit">Add Customer</button>
    </form>
  )
}

export default CustomerForm