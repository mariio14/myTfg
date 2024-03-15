import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';
import {AcademicYearSelector, CategorySelector} from "../index";
import 'react-datepicker/dist/react-datepicker.css';
import DatePicker from 'react-datepicker';
import { Client } from '@stomp/stompjs';


const Post = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
	const [academicYear, setAcademicYear] = useState('');
	const [subjectId, setSubjectId] = useState('');
    const [mode, setMode] = useState('Producto');
	const [backendErrors, setBackendErrors] = useState(null);
    
    let form;


	const handleFileRead =  async (event) => {
		const file = event.target.files;
		const base64 = [];
		for(let i=0; i<file.length; i++){
			base64.push(await convertBase64(file[i]));

		}
		//setImages(base64);
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
            dispatch(actions.postear(
                titulo,
                descripcion,
                subjectId,
				academicYear,
                () => {
					navigate('/*');
				},
				() => setBackendErrors(setBackendErrors("Usuario no encontrado")),
                () => {
                    navigate('/');
                }));

        } else {
			setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (
        <div>			
			<div className="container mt-4">
			    <div className="row justify-content-center">
			        <div className="col-md-6">
			            <div className="card bg-darkgray rounded-3">
			                <h5 className="card-header bg-black">
			                    <div className="btn-group" role="group">
                  					<button
                    					className={`btn btn-dark ${mode === 'Producto' ? 'active' : ''}`}
                    					onClick={() => setMode('Producto')}
										data-testid="post-button-product">
                    					Producto
                  					</button>
                  				<button
                    				className={`btn btn-dark ${mode === 'Cupón' ? 'active' : ''}`}
                    					onClick={() => setMode('Cupón')}
										data-testid="post-button-cupon">
                    					Cupón
                  					</button>
                			</div>
			                </h5>
			                <div className="card-body">
			                    <form ref={node => form = node} 
			                        className="needs-validation" noValidate
									data-testid="post-form"
			                        onSubmit={e => handleSubmit(e)}>
			                        <div className="form-group col">
			                            <label htmlFor="title" className="col-md-12 col-form-label">
			                                Titulo
			                            </label>
			                            <div className="col-md-6">
			                                <input type="text" id="titulo" className="form-control"
			                                    value={titulo}
												data-testid="post-input-title"
			                                    onChange={e => setTitulo(e.target.value)}
			                                    autoFocus
			                                    required/>
			                                <div className="invalid-feedback">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                         <div className="form-group col">
			                            <label htmlFor="descripcion" className="col-md-12 col-form-label">
			                                Descripcion
			                            </label>
			                            <div className="col-md-12">
			                                <input type="text" id="descripcion" className="form-control" data-testid="post-input-desc"
			                                    value={descripcion}
			                                    onChange={e => setDescripcion(e.target.value)}
			                                    autoFocus
			                                    required/>
			                                <div className="invalid-feedback">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                         
									<div className="form-group col">
										<label htmlFor="url" className="col-md-12 col-form-label">
											AcademicYear
										</label>
										<div className="col-md-6">
											<AcademicYearSelector
												id="academicYear"
												data-testid="post-year-pick"
												className={`custom-select my-1 mr-sm-2 form-control`}
												value={academicYear}
												onChange={e => setAcademicYear(e.target.value)}
												required
											/>
											<div className="invalid-feedback">
												Campo requerido
											</div>

										</div>
									</div>
									
			                        <div className="form-group col">
			                        		<label htmlFor="formFileMultiple" className="col-md-12 col-form-label">
				                                Imagen
				                            </label>
										
										<div className="col-md-6">
											<input className="form-control" data-testid="post-image-pick" type="file" id="formFileMultiple" multiple accept="image/*" onChange={handleFileRead}/>
										</div>	
										
									</div>
									<div className="form-group col">
										<label htmlFor="formFileMultiple" className="col-md-12 col-form-label">
											Universidad
										</label>
										<div className="col-md-6">
											<CategorySelector
												id="categoryId"
												data-testid="post-category-pick"
												className={`custom-select my-1 mr-sm-2 form-control`}
												value={subjectId}
												onChange={e => setSubjectId(e.target.value)}
												required
											/>
											<div className="invalid-feedback">
												Campo requerido
											</div>

										</div>
									</div>
									{backendErrors?<p style={{color:'red', textAlign:'center', marginTop:'30px', marginBottom:'20px'}}>{backendErrors}</p>:null}
			                        <div className="form-group col">
			                            <div className="col-md-1">
			                            	<br/>
			                                <button type="submit" className="btn btn-primary btn-dark" id="loginButton" data-testid="post-button-publicar">
			                                    Publicar
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

export default Post;
