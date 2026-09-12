import axios from 'axios'

const BASE_URL = 'https://milk-delivery-tracker.onrender.com/api/dashboard'

// Get summary for a given year/month
export const getDashboardSummary = (year, month) => {
  return axios.get(`${BASE_URL}/summary`, {
    params: {
      year: year,
      month: month
    }
  })
}

// Get daily breakdown for the month (for line/bar chart)
export const getDailyBreakdown = (year, month) => {
  return axios.get(`${BASE_URL}/daily-breakdown`, {
    params: { year: year, month: month }
  })
}

// Get customer breakdown for the month (for pie chart)
export const getCustomerBreakdown = (year, month) => {
  return axios.get(`${BASE_URL}/customer-breakdown`, {
    params: { year: year, month: month }
  })
}