import '../styles/Toast.css'

function Toast({ message, type, visible }) {
  if (!visible) return null

  return (
    <div className={`toast toast-${type}`}>
      <span className="toast-icon">{type === 'success' ? '✓' : '✕'}</span>
      <span className="toast-message">{message}</span>
    </div>
  )
}

export default Toast