import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/settings'

// Get current prices
export const getCurrentPrices = () => {
  return axios.get(`${BASE_URL}/prices`)
}

// Update prices
export const updatePrices = (milkPrice, waterPrice) => {
  return axios.put(`${BASE_URL}/prices`, {
    milkPricePerLiter: milkPrice,
    waterPricePerLiter: waterPrice
  })
}