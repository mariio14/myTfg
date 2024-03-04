import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common/';
import * as actions from '../actions';

const Login = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const [avatar, setAvatar] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.login(
                userName.trim(),
                password,
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

    return (
		/*<Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/> Esto va justo antes de <div className="card bg-light border-dark">*/
        <div>			
			<div className="container mt-4">
			    <div className="row justify-content-center">
			        <div className="col-md-6">
			            <div className="card bg-darkgray rounded-3">
			                <h5 className="card-header bg-black">
			                    Autenticarse
			                </h5>
			                <div className="card-body">
			                    <form ref={node => form = node} 
			                        className="needs-validation" noValidate 
			                        onSubmit={e => handleSubmit(e)}>
			                        <div className="form-group col">
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
			                        <div className="form-group col">
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
			                        <div className="form-group" style={{textAlign: 'center'}}>
			                            <div style={{margin:'1em auto'}}>
			                                <button type="submit" className="btn btn-primary btn-dark" id="loginButton">
			                                    Autenticarse
			                                </button>
			                            </div>
			                            
			                           	<div style={{fontWeight: 'normal', marginTop: '1rem'}}>
            							¿No tienes cuenta? {' '}
                							<Link to="/users/signup" style={{textDecoration: 'none', color:'black'}}>
                    						Regístrate
                							</Link>
            							</div>
			                        </div>
			                    </form>
			                </div>
			            </div>
					</div>
				</div>
            </div>
        </div>       
    );

}

export default Login;
