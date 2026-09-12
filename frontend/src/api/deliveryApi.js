import axios from 'axios'

const BASE_URL = 'https://milk-delivery-tracker.onrender.com/api/delivery-logs'

// Mark delivery (or update it) for a customer on a specific date
export const markDelivery = (customerId, date, delivered) => {
    return axios.post(`${BASE_URL}/mark`, null, {
        params: {
            customerId: customerId,
            date: date,
            delivered: delivered
        }
    })
}

// Get all delivery logs for a customer
export const getLogsForCustomer = (customerId) => {
  return axios.get(`${BASE_URL}/customer/${customerId}`)
}

// Get monthly bill for a customer
export const getMonthlyBill = (customerId, year, month) => {
  return axios.get(`${BASE_URL}/bill`, {
    params: {
      customerId: customerId,
      year: year,
      month: month
    }
  })
}