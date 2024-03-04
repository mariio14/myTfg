import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import Footer from './Footer';
import users from '../../users';

const Header = () => {
  const userName = useSelector(users.selectors.getUserName);

  return (

    <nav className="navbar navbar-expand-lg navbar-light bg-black border App-header">
      <Link className="navbar-brand text-light font-weight-bold" to="/">
        MYTFG | Inicio
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse right" id="navbarSupportedContent">
        <ul className="navbar-nav">
          {userName ? (
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/users/logout">
                  Logout
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/users/change-password">
                  Cambiar contraseña
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/users/setAvatar">
                  Cambiar Avatar
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/users/update-profile">
                  Actualizar Perfil
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="users/createPost">
                  Publicar
                </Link>
              </li>              
            </>
          ) : (
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/users/login">
                  Login
                </Link>
              </li>
            </>
          )}
        </ul>
      </div>
      
    </nav>

    
  );
};

export default Header;
