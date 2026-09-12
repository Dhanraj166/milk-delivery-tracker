import { useState, useEffect } from 'react'
import { getCurrentPrices, updatePrices } from '../api/settingsApi'
import Toast from '../components/Toast'
import '../styles/Settings.css'

function Settings() {
  const [milkPrice, setMilkPrice] = useState('')
  const [waterPrice, setWaterPrice] = useState('')
  const [loading, setLoading] = useState(true)
  const [saving, setSaving] = useState(false)
  const [toast, setToast] = useState({ visible: false, message: '', type: 'success' })

  useEffect(() => {
    fetchPrices()
  }, [])

  const fetchPrices = () => {
    getCurrentPrices()
      .then((response) => {
        setMilkPrice(response.data.milkPricePerLiter)
        setWaterPrice(response.data.waterPricePerLiter)
        setLoading(false)
      })
      .catch((error) => {
        console.error('Error fetching prices:', error)
        setLoading(false)
      })
  }

  const showToast = (message, type) => {
    setToast({ visible: true, message, type })

    setTimeout(() => {
      setToast((prev) => ({ ...prev, visible: false }))
    }, 2500)
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    setSaving(true)

    updatePrices(parseFloat(milkPrice), parseFloat(waterPrice))
      .then(() => {
        setSaving(false)
        showToast('Prices updated successfully', 'success')
      })
      .catch((error) => {
        console.error('Error updating prices:', error)
        setSaving(false)
        showToast('Failed to update prices', 'error')
      })
  }

  if (loading) {
    return <p>Loading settings...</p>
  }

  return (
    <div>
      <div className="page-header">
        <div>
          <h2>Price Settings</h2>
          <p className="page-subtitle">Update the standard rate per liter</p>
        </div>
      </div>

      <div className="card settings-card">
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Milk Price (₹ per Liter)</label>
            <input
              type="number"
              step="0.1"
              value={milkPrice}
              onChange={(e) => setMilkPrice(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Water Price (₹ per Liter)</label>
            <input
              type="number"
              step="0.1"
              value={waterPrice}
              onChange={(e) => setWaterPrice(e.target.value)}
              required
            />
          </div>

          <button className="submit-btn" type="submit" disabled={saving}>
            {saving ? 'Saving...' : 'Save Prices'}
          </button>
        </form>
      </div>

      <Toast message={toast.message} type={toast.type} visible={toast.visible} />
    </div>
  )
}

export default Settings