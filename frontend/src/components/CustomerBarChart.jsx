import { Bar } from 'react-chartjs-2'

function CustomerBarChart({ customerStats }) {

  const chartData = {
    labels: customerStats.map((c) => c.customerName),
    datasets: [
      {
        label: 'Revenue (₹)',
        data: customerStats.map((c) => c.revenue),
        backgroundColor: '#4ea8de',
        borderRadius: 4
      }
    ]
  }

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false
      },
      title: {
        display: true,
        text: 'Revenue by Customer'
      }
    },
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }

  return (
    <div className="chart-canvas-wrapper">
      <Bar data={chartData} options={chartOptions} />
    </div>
  )
}

export default CustomerBarChart