import { Pie } from 'react-chartjs-2'

const COLORS = [
  '#4ea8de', '#ff6b6b', '#ffd166', '#06d6a0', '#a78bfa',
  '#f4978e', '#5aa9e6', '#8ac926', '#ff9f1c', '#c77dff'
]

function CustomerPieChart({ customerStats }) {

  const chartData = {
    labels: customerStats.map((c) => c.customerName),
    datasets: [
      {
        label: 'Revenue (₹)',
        data: customerStats.map((c) => c.revenue),
        backgroundColor: customerStats.map((_, index) => COLORS[index % COLORS.length]),
        borderWidth: 1
      }
    ]
  }

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: true,
        position: 'right'
      },
      title: {
        display: true,
        text: 'Revenue Contribution by Customer'
      }
    }
  }

  return (
    <div className="chart-canvas-wrapper">
      <Pie data={chartData} options={chartOptions} />
    </div>
  )
}

export default CustomerPieChart