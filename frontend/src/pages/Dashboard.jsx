import { useState, useEffect } from 'react'
import { getDashboardSummary, getDailyBreakdown, getCustomerBreakdown } from '../api/dashboardApi'
import DailyTrendChart from '../components/DailyTrendChart'
import CustomerPieChart from '../components/CustomerPieChart'
import CustomerBarChart from '../components/CustomerBarChart'
import '../styles/Dashboard.css'

const MONTH_NAMES = [
  'January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December'
]

function Dashboard() {
  const now = new Date()

  const [selectedYear, setSelectedYear] = useState(now.getFullYear())
  const [selectedMonth, setSelectedMonth] = useState(now.getMonth() + 1)

  const [summary, setSummary] = useState(null)
  const [dailyStats, setDailyStats] = useState([])
  const [customerStats, setCustomerStats] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    fetchAllDashboardData(selectedYear, selectedMonth)
  }, [selectedYear, selectedMonth])

  const fetchAllDashboardData = (year, month) => {
    setLoading(true)

    getDashboardSummary(year, month)
      .then((response) => {
        setSummary(response.data)
      })
      .catch((error) => {
        console.error('Error fetching dashboard summary:', error)
      })

    getDailyBreakdown(year, month)
      .then((response) => {
        setDailyStats(response.data)
      })
      .catch((error) => {
        console.error('Error fetching daily breakdown:', error)
      })

    getCustomerBreakdown(year, month)
      .then((response) => {
        setCustomerStats(response.data)
        setLoading(false)
      })
      .catch((error) => {
        console.error('Error fetching customer breakdown:', error)
        setLoading(false)
      })
  }

  // Build a simple list of years to choose from: current year and the previous year
  const yearOptions = [now.getFullYear(), now.getFullYear() - 1]

  return (
    <div>
      <div className="dashboard-header-row">
        <h2>Dashboard</h2>

        <div className="month-selector">
          <select
            value={selectedMonth}
            onChange={(e) => setSelectedMonth(Number(e.target.value))}
          >
            {MONTH_NAMES.map((name, index) => (
              <option key={index} value={index + 1}>{name}</option>
            ))}
          </select>

          <select
            value={selectedYear}
            onChange={(e) => setSelectedYear(Number(e.target.value))}
          >
            {yearOptions.map((year) => (
              <option key={year} value={year}>{year}</option>
            ))}
          </select>
        </div>
      </div>

      {loading ? (
        <p>Loading dashboard...</p>
      ) : !summary ? (
        <p>Could not load dashboard data.</p>
      ) : (
        <>
          <div className="stats-grid">
            <div className="stat-card">
              <p className="stat-label">Total Customers</p>
              <h3 className="stat-value">{summary.totalCustomers}</h3>
            </div>

            <div className="stat-card">
              <p className="stat-label">Total Liters Sold</p>
              <h3 className="stat-value">{summary.totalLitersSold} L</h3>
            </div>

            <div className="stat-card">
              <p className="stat-label">Total Revenue</p>
              <h3 className="stat-value">₹{summary.totalRevenue}</h3>
            </div>

            <div className="stat-card">
              <p className="stat-label">Total Deliveries</p>
              <h3 className="stat-value">{summary.totalDeliveredEntries}</h3>
            </div>
          </div>

          <div className="charts-row">
            <div className="chart-section">
              {dailyStats.length === 0 ? (
                <p>No delivery data for this month to chart.</p>
              ) : (
                <DailyTrendChart dailyStats={dailyStats} />
              )}
            </div>

            <div className="chart-section chart-small">
              {customerStats.length === 0 ? (
                <p>No customer data yet.</p>
              ) : (
                <CustomerPieChart customerStats={customerStats} />
              )}
            </div>
          </div>

          <div className="chart-section chart-full">
            {customerStats.length === 0 ? (
              <p>No customer data yet.</p>
            ) : (
              <CustomerBarChart customerStats={customerStats} />
            )}
          </div>
        </>
      )}
    </div>
  )
}

export default Dashboard