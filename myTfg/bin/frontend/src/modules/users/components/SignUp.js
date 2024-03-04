import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';

const SignUp = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail]  = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const [passwordsDoNotMatch, setPasswordsDoNotMatch] = useState(false);
    let form;
    let confirmPasswordInput;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity() && checkConfirmPassword()) {
            
            dispatch(actions.signUp(
                {userName: userName.trim(),
                password: password,
                firstName: firstName.trim(),
                lastName: lastName.trim(),
                email: email.trim()},
                () => navigate('/'),
                errors => setBackendErrors(errors),
                () => {
                    navigate('/users/login');
                    dispatch(actions.logout());
                }
            ));
            

        } else {

            setBackendErrors(null);
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
        
     	<div className="container mt-4">
			<div className="row justify-content-center">
			    <div className="col-md-6">
		            <div className="card bg-darkgray rounded-3">
		                <h5 className="card-header bg-black">
		                   	Registrarse
		                </h5>
		                <div className="card-body">
		                    <form ref={node => form = node}
		                        className="needs-validation" noValidate 
		                        onSubmit={e => handleSubmit(e)}>
		                        <div className="form-group ">
		                            <label htmlFor="userName" className="col-md-3 col-form-label" style={{width:'100%'}}>
		                                Usuario
		                            </label>
		                            <div className="col-md-4" style={{width:'100%'}}>
		                                <input type="text" id="userName" className="form-control"
		                                    value={userName}
		                                    onChange={e => setUserName(e.target.value)}
		                                    autoFocus
		                                    required/>
		                                <div className="invalid-feedback">
		                                    Campo requerido
		                                </div>
		                            </div>
		                        </div>
		                        <div className="form-group ">
		                            <label htmlFor="password" className="col-md-3 col-form-label" style={{width:'100%'}}>
		                                Contraseña
		                            </label>
		                            <div className="col-md-4" style={{width:'100%'}}>
		                                <input type="password" id="password" className="form-control"
		                                    value={password}
		                                    onChange={e => setPassword(e.target.value)}
		                                    required/>
		                                <div className="invalid-feedback">
		                                    Campo requerido
		                                </div>
		                            </div>
		                        </div>
		                        <div className="form-group ">
		                            <label htmlFor="confirmPassword" className="col-md-3 col-form-label" style={{width:'100%'}}>
		                                Confirmar contraseña
		                            </label>
		                            <div className="col-md-4" style={{width:'100%'}}>
		                                <input ref={node => confirmPasswordInput = node}
		                                    type="password" id="confirmPassword" className="form-control"
		                                    value={confirmPassword}
		                                    onChange={e => handleConfirmPasswordChange(e.target.value)}
		                                    required/>
		                                <div className="invalid-feedback">
		                                    {passwordsDoNotMatch ?
		                                        "Las contraseñas no coinciden" :
		                                        "Campo requerido"}
		                                </div>
		                            </div>
		                        </div>
		                        <div className="form-group ">
		                            <label htmlFor="firstName" className="col-md-3 col-form-label" style={{width:'100%'}}>
		                                Nombre
		                            </label>
		                            <div className="col-md-4" style={{width:'100%'}}>
		                                <input type="text" id="firstName" className="form-control"
		                                    value={firstName}
		                                    onChange={e => setFirstName(e.target.value)}
		                                    required/>
		                                <div className="invalid-feedback">
		                                    Campo requerido
		                                </div>
		                            </div>
		                        </div>
		                        <div className="form-group ">
		                            <label htmlFor="lastName" className="col-md-3 col-form-label" style={{width:'100%'}}>
		                                Apellido
		                            </label>
		                            <div className="col-md-4" style={{width:'100%'}}>
		                                <input type="text" id="lastName" className="form-control"
		                                    value={lastName}
		                                    onChange={e => setLastName(e.target.value)}
		                                    required/>
		                                <div className="invalid-feedback">
		                                    Campo requerido
		                                </div>
		                            </div>
		                        </div>
		                        <div className="form-group ">
		                            <label htmlFor="email" className="col-md-3 col-form-label" style={{width:'100%'}}>
		                                Email
		                            </label>
		                            <div className="col-md-4" style={{width:'100%'}}>
		                                <input type="email" id="email" className="form-control"
		                                    value={email}
		                                    onChange={e => setEmail(e.target.value)}
		                                    required/>
		                                <div className="invalid-feedback">
		                                    Introduzca una dirección de correo electrónico correcta
		                                </div>
		                            </div>
		                        </div>
		                        <div className="form-group" style={{textAlign: 'center'}}>
		                            <div style={{margin:'1em auto'}}>
		                                <button type="submit" className="btn btn-primary btn-dark" id="loginButton">
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
