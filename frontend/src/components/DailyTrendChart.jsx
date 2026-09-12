import { Line } from 'react-chartjs-2'

function DailyTrendChart({ dailyStats }) {

  const chartData = {
    labels: dailyStats.map((stat) => stat.date),
    datasets: [
      {
        label: 'Liters Sold',
        data: dailyStats.map((stat) => stat.liters),
        borderColor: '#4ea8de',
        backgroundColor: 'rgba(78, 168, 222, 0.2)',
        tension: 0.3,
        fill: true
      }
    ]
  }

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: true,
        position: 'top'
      },
      title: {
        display: true,
        text: 'Daily Liters Sold This Month'
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
      <Line data={chartData} options={chartOptions} />
    </div>
  )
}

export default DailyTrendChart