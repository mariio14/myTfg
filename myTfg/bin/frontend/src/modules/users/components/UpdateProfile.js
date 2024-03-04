import {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const UpdateProfile = () => {

    const user = useSelector(selectors.getUser);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState(user.firstName);
    const [lastName, setLastName] = useState(user.lastName);
    const [email, setEmail]  = useState(user.email);
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            
            dispatch(actions.updateProfile(
                {id: user.id,
                firstName: firstName.trim(),
                lastName: lastName.trim(),
                email: email.trim()},
                () => navigate('/'),
                errors => setBackendErrors(errors)));

        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }
	//<Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
    return (
        <div>
            <div className="card bg-light border-dark" style={{margin:'5vh 10vw'}}>
                <h5 className="card-header">
                    Actualizar Perfil
                </h5>
                <div className="card-body">
                    <form ref={node => form = node} 
                        className="needs-validation" noValidate onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row" style={{textAlign:'center', margin:'1em auto'}}>
                            <label htmlFor="firstName" className="col-md-3 col-form-label">
                                Nombre
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="firstName" className="form-control"
                                    value={firstName}
                                    onChange={e => setFirstName(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    Campo requerido
                                </div>
                            </div>
                        </div>
                        <div className="form-group row" style={{textAlign:'center', margin:'1em auto'}}>
                            <label htmlFor="lastName" className="col-md-3 col-form-label">
                                Apellido
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="lastName" className="form-control"
                                    value={lastName}
                                    onChange={e => setLastName(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    Campo requerido
                                </div>
                            </div>
                        </div>
                        <div className="form-group row" style={{textAlign:'center', margin:'1em auto'}}>
                            <label htmlFor="email" className="col-md-3 col-form-label">
                                Email
                            </label>
                            <div className="col-md-4">
                                <input type="email" id="email" className="form-control"
                                    value={email}
                                    onChange={e => setEmail(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    Email requerido
                                </div>
                            </div>
                        </div>
                        <div className="form-group">
                            <div style={{textAlign:'center', margin:'1em auto'}}>
                                <button type="submit" className="btn btn-primary btn-dark" style={{width:'100px'}}>
                                    Guardar
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default UpdateProfile;