import {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Avatar} from "@mui/material";

const UpdateProfile = () => {

    const user = useSelector(selectors.getUser);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState(user.firstName);
    const [lastName, setLastName] = useState(user.lastName);
    const [email, setEmail]  = useState(user.email);
    const [avatar, setAvatar]  = useState(user.avatar);
    const [setBackendErrors] = useState(null);
    let form;

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

        if (form.checkValidity()) {

            dispatch(actions.updateProfile(
                {id: user.id,
                    firstName: firstName.trim(),
                    lastName: lastName.trim(),
                    email: email.trim(),
                    avatar: avatar},
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
			<div className="container mt-4">
			    <div className="row justify-content-center">
			        <div className="col-md-6">
			            <div className="card bg-darkgray rounded-3">
			                <h5 className="card-header bg-black">
			                    Actualizar Perfil
			                </h5>
			                <div className="card-body">
			                    <form ref={node => form = node} data-testid="updateProfile-form"
			                          className="needs-validation" noValidate onSubmit={e => handleSubmit(e)}>
			                        <div className="form-group col">
			                            <label htmlFor="firstName" className="col-md-12 col-form-label">
			                                Nombre
			                            </label>
			                            <div className="col-md-6">
			                                <input type="text" id="firstName" className="form-control" data-testid="updateProfile-name-input"
			                                       value={firstName}
			                                       onChange={e => setFirstName(e.target.value)}
			                                       autoFocus
			                                       required/>
			                                <div className="invalid-feedback" data-testid="updateProfile-name-mandatory">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                        <div className="form-group col">
			                            <label htmlFor="lastName" className="col-md-12 col-form-label">
			                                Apellido
			                            </label>
			                            <div className="col-md-6">
			                                <input type="text" id="lastName" className="form-control" data-testid="updateProfile-surname-input"
			                                       value={lastName}
			                                       onChange={e => setLastName(e.target.value)}
			                                       required/>
			                                <div className="invalid-feedback" data-testid="updateProfile-surname-mandatory">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                        <div className="form-group col">
			                            <label htmlFor="email" className="col-md-12 col-form-label">
			                                Email
			                            </label>
			                            <div className="col-md-6">
			                                <input type="email" id="email" className="form-control" data-testid="updateProfile-email-input"
			                                       value={email}
			                                       onChange={e => setEmail(e.target.value)}
			                                       required/>
			                                <div className="invalid-feedback" data-testid="updateProfile-email-mandatory">
			                                    Email requerido
			                                </div>
			                            </div>
			                        </div>
			                        <div className="form-group col">
			                            <label htmlFor="avatar" className="col-md-12 col-form-label">
			                                Avatar
			                            </label>
			                            <Avatar alt="Remy Sharp" src={"data:image/jpeg;base64," + avatar} sx={{ width: 100, height: 100 }}/>
			                            <div className="col-md-6" style={{ marginTop: '1rem', marginBottom: '1.5rem' }}>
			                                <input
												data-testid="avatar-selector"
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
			                                <button type="submit" className="btn btn-primary btn-dark" id="saveProfileButton" data-testid="updateProfile-submit-button">
			                                    Guardar
			                                </button>
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

export default UpdateProfile;