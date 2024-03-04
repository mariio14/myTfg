import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';
import { Avatar } from '@mui/material';

const SignUp = () => {

	const dispatch = useDispatch();
	const navigate = useNavigate();
	const [userName, setUserName] = useState('');
	const [password, setPassword] = useState('');
	const [confirmPassword, setConfirmPassword] = useState('');
	const [firstName, setFirstName] = useState('');
	const [lastName, setLastName] = useState('');
	const [email, setEmail]  = useState('');
	const [avatar, setAvatar]  = useState('');;
	const [passwordsDoNotMatch, setPasswordsDoNotMatch] = useState(false);
	let form;
	let confirmPasswordInput;

	const handleFileRead =  async (event) => {
		const file = event.target.files;
		const base64 = [];
		for(let i=0; i<file.length; i++){
			base64.push(await convertBase64(file[i]));

		}
		const combinedBase64 = base64.join('');

		setAvatar(combinedBase64);
	}

	let convertBase64 = (file) => {
		return new Promise((resolve, reject) => {
			const fileReader = new FileReader();
			fileReader.readAsDataURL(file)
			fileReader.onload = () => {
				resolve(fileReader.result);
			}
			fileReader.onerror = (error) => {
				reject(error);
			}
		})
	}

	const handleSubmit = event => {

		event.preventDefault();

		if (form.checkValidity() && checkConfirmPassword()) {

			dispatch(actions.signUp(
				{userName: userName.trim(),
					password: password,
					firstName: firstName.trim(),
					lastName: lastName.trim(),
					email: email.trim(),
					avatar: avatar},
				() => navigate('/'),
				() => {
					navigate('/users/login');
					dispatch(actions.logout());
				}
			));


		} else {
			form.classList.add('was-validated');

		}

	}

	const checkConfirmPassword = () => {

		if (password !== confirmPassword) {

			confirmPasswordInput.setCustomValidity('error');
			setPasswordsDoNotMatch(true);

			return false;

		} else {
			return true;
		}

	}

	const handleConfirmPasswordChange = value => {

		confirmPasswordInput.setCustomValidity('');
		setConfirmPassword(value);
		setPasswordsDoNotMatch(false);

	}
	/*<Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/> va antes de   <div className="card bg-light border-dark">*/
	return (

		<div className="container mt-4" style={{paddingBottom: '3rem'}}>
			<div className="row justify-content-center">
				<div className="col-md-6">
					<div className="card bg-darkgray rounded-3">
						<h5 className="card-header bg-black">
							Registrarse
						</h5>
						<div className="card-body">
							<form ref={node => form = node} data-testid="signUp-form"
								  className="needs-validation" noValidate
								  onSubmit={e => handleSubmit(e)}>
								<div className="form-group col">
									<label htmlFor="userName" className="col-md-12 col-form-label">
										Usuario
									</label>
									<div className="col-md-6">
										<input type="text" id="userName" className="form-control" data-testid="signUp-username-input"
											   value={userName}
											   onChange={e => setUserName(e.target.value)}
											   autoFocus
											   required/>
										<div className="invalid-feedback">
											Campo requerido
										</div>
									</div>
								</div>
								<div className="form-group col">
									<label htmlFor="password" className="col-md-12 col-form-label">
										Contraseña
									</label>
									<div className="col-md-6">
										<input type="password" id="password" className="form-control" data-testid="signUp-password-input"
											   value={password}
											   onChange={e => setPassword(e.target.value)}
											   required/>
										<div className="invalid-feedback">
											Campo requerido
										</div>
									</div>
								</div>
								<div className="form-group col">
									<label htmlFor="confirmPassword" className="col-md-12 col-form-label">
										Confirmar contraseña
									</label>
									<div className="col-md-6">
										<input ref={node => confirmPasswordInput = node}
											   type="password" id="confirmPassword" className="form-control" data-testid="signUp-confirm-password-input"
											   value={confirmPassword}
											   onChange={e => handleConfirmPasswordChange(e.target.value)}
											   required/>
										<div className="invalid-feedback" data-testid="signUp-password-incorrect">
											{passwordsDoNotMatch ?
												"Las contraseñas no coinciden" :
												"Campo requerido"}
										</div>
									</div>
								</div>
								<div className="form-group col">
									<label htmlFor="firstName" className="col-md-12 col-form-label">
										Nombre
									</label>
									<div className="col-md-8">
										<input type="text" id="firstName" className="form-control" data-testid="signUp-firstName-input"
											   value={firstName}
											   onChange={e => setFirstName(e.target.value)}
											   required/>
										<div className="invalid-feedback">
											Campo requerido
										</div>
									</div>
								</div>
								<div className="form-group col">
									<label htmlFor="lastName" className="col-md-3 col-form-label">
										Apellido
									</label>
									<div className="col-md-8">
										<input type="text" id="lastName" className="form-control" data-testid="signUp-lastName-input"
											   value={lastName}
											   onChange={e => setLastName(e.target.value)}
											   required/>
										<div className="invalid-feedback">
											Campo requerido
										</div>
									</div>
								</div>
								<div className="form-group col">
									<label htmlFor="email" className="col-md-3 col-form-label">
										Email
									</label>
									<div className="col-md-8">
										<input type="email" id="email" className="form-control" data-testid="signUp-email-input"
											   value={email}
											   onChange={e => setEmail(e.target.value)}
											   required/>
										<div className="invalid-feedback">
											Introduzca una dirección de correo electrónico correcta
										</div>
									</div>
								</div>
								<div className="form-group col">
									<label htmlFor="avatar" className="col-md-12 col-form-label">
										Avatar
									</label>
									<Avatar alt="Remy Sharp" src={avatar} sx={{ width: 100, height: 100 }}/>
									<div className="col-md-6"  style={{ marginTop: '1rem', marginBottom: '1.5rem' }}>
										<input
											data-testid="signUp-avatar-input"
											type="file"
											id="avatar"
											className="form-control"
											accept="image/*"
											onChange={handleFileRead}
										/>
									</div>
								</div>
								<div className="form-group col">
									<div className="col-md-1">
										<button type="submit" className="btn btn-primary btn-dark" id="signUpButton" data-testid="signUp-submit-button">
											Registrarse
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	);

}

export default SignUp;