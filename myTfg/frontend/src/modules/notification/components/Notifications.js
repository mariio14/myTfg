import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import * as actions from '../actions';
import * as selectors from "../selectors";
import React, { useEffect, useState } from "react";
import SvgIcon from '@mui/material/SvgIcon';
import IconButton from '@mui/material/IconButton';
import { Avatar } from '@mui/material';

const Notifications = () => {
	
	const dispatch = useDispatch();
	const navigate = useNavigate();
	const notifications = useSelector(selectors.getNotificationsByUser);
	const [update, setUpdate] = useState(false);
	let text = "";
	
	useEffect(() => {

		dispatch(actions.findNotificationsByUser());

		return () => {
			dispatch(actions.getNotificationsByUserClear());
			dispatch(actions.getNotReadNotifications());
		};
	}, [dispatch, update])
	
	const navigateToAny = (redirect, id, userName) => {
		if (redirect === "message") {
			const path = `/messages/${id}/${userName}`;
			navigate(path);
		}
		else{
			const path = `/feed/posts/${id}`;
			navigate(path);
		}
	}
	
	const handleDelete = (event, notificationId) => {
		
		event.preventDefault();
		event.stopPropagation();
		if(!Number.isNaN(notificationId)){
			dispatch(actions.deleteNotification(notificationId));
       		setUpdate(prevUpdate => !prevUpdate);
        }
	}
	
	const CloseIcon = (props) => (
        <SvgIcon {...props}>
            <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
        </SvgIcon>
    );
    
    
	
	if (!notifications || notifications.length === 0) {
	  text = "No hay ninguna notificación";
	} else {
	  text = "";
	}
	
	return(
		<div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
		 <h2 style={{ fontSize: '40px', textAlign: 'center', paddingTop: '0.2rem', paddingBottom: '1rem' }}>
		   {text}
		 </h2>
		 <div>
		   {notifications && notifications.map((notification, idx) => (
		     <div key={idx} style={{margin:'2em'}}>
		       <div className={`card post-card ${notification.read ? 'bg-white' : 'bg-lightcoral'} rounded-4`}
		         onClick={() => navigateToAny(notification.redirect, notification.redirectId, notification.redirectUserName)}
		         style={{ display: 'flex', flexDirection: 'row', minHeight:'50px'}}>
		         <div className="post-content" style={{width: '100%', display:'block', margin:'auto 0px'}}>
		           <div className="post-text">
		             <div className="box" style={{display:'flex', margin:'auto 0'}}>
		               <Avatar data-testid={`notification-avatar-${idx}`} style={{margin:'auto 20px'}} alt="Remy Sharp" src={"data:image/jpeg;base64," + notification.avatar} sx={{ width: 45, height: 45}}/>
		               <h2 style={{borderBottom:'none'}} data-testid={`notification-text-${idx}`}>
						   {notification.text}
		                </h2>
		             </div>
		           </div>
		         </div>
		         <IconButton data-testid={`notification-button-${idx}`} style={{height:'40px', margin:'auto 0'}}  onClick={(e) => handleDelete(e, notification.id)}>
                	<CloseIcon />
            	 </IconButton>
		     </div>
		       </div>
		   ))}
		 </div>
		</div>
	)	

}
export default Notifications