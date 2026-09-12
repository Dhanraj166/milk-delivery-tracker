import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/customers'

// Get all customers
export const getAllCustomers = () => {
  return axios.get(BASE_URL)
}

// Add a new customer
export const addCustomer = (customer) => {
  return axios.post(BASE_URL, customer)
}

//Get one customer by id
export const getCustomerById = (id) => {
  return axios.get(`${BASE_URL}/${id}`)
}