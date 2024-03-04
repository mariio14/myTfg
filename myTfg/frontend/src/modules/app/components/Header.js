import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import users from '../../users';
import notification from '../../notification';
import { Avatar } from '@mui/material';
import Badge from '@mui/material/Badge';


const Header = () => {

  const dispatch = useDispatch();
  const userName = useSelector(users.selectors.getUserName);
  const avatar = useSelector(users.selectors.getAvatar);
  const notifications = useSelector(notification.selectors.getNotReadNotifications);
    
  useEffect(() => {
	  if(userName)
	  	dispatch(notification.actions.getNotReadNotifications());
    }, [dispatch, userName]);
  
  if (notifications == null)
  	return

  return (

    <nav className="navbar navbar-expand-lg navbar-light sticky-top bg-black border App-header">
      <Link className="navbar-brand text-light font-weight-bold" to="/">
        MYTFG | Inicio
      </Link>
      <button
        className="navbar-toggler ms-auto"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
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
                <Link className="nav-link" to="/notifications/notifications">
            	  <Badge badgeContent={notifications} color="error" showZero>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-bell" viewBox="0 0 16 16">
                      <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
                    </svg>
            	  </Badge>
            	</Link>
    		  </li>
              <li className="nav-item">
                <Avatar alt="Remy Sharp" src={"data:image/jpeg;base64," + avatar} sx={{ width: 45, height: 45 }}/>
              </li>
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
                <Link className="nav-link" to="/users/update-profile">
                  Actualizar Perfil
                </Link>
              </li>
                <li className="nav-item">
                    <Link className="nav-link" to="users/myFeed">
                        Mis Publicaciones
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