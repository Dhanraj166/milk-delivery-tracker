import { Link, useLocation } from 'react-router-dom'
import Logo from './Logo'
import '../styles/Navbar.css'

function Navbar() {
  const location = useLocation()

  const isActive = (path) => location.pathname === path

  return (
    <nav className="navbar">
      <Logo />

      <div className="nav-links">
        <Link to="/" className={isActive('/') ? 'nav-link active' : 'nav-link'}>
          Dashboard
        </Link>
        <Link to="/customers" className={isActive('/customers') ? 'nav-link active' : 'nav-link'}>
          Customers
        </Link>
        <Link to="/settings" className={isActive('/settings') ? 'nav-link active' : 'nav-link'}>
          Settings
        </Link>
      </div>
    </nav>
  )
}

export default Navbar