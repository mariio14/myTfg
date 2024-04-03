import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';
import {AcademicYearSelector, CategorySelector} from "../index";
import 'react-datepicker/dist/react-datepicker.css';
import { Client } from '@stomp/stompjs';
import SubjectSelector from "./SubjectSelector";


const Post = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
	const [academicYear, setAcademicYear] = useState('');
	const [uniId, setUniId] = useState('');
	const [subjectId, setSubjectId] = useState('');
	const [files, setFiles] = useState([]);
	const [backendErrors, setBackendErrors] = useState(null);
    
    let form;

	const handleFileRead = (event) => {
		setFiles([...event.target.files]);
	}

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
			const formData = new FormData();
			//formData.append('files', files);
			files.forEach((file, index) => {
				formData.append(`file${index}`, file);
			});
			formData.append('titulo', titulo);
			formData.append('descripcion', descripcion);
			formData.append('academicYear', academicYear);
			formData.append('subjectId', subjectId);
			formData.append('etiquetas', '');
            dispatch(actions.postear(
                formData,
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
											Academic Year
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
				                                Fichero(s)
				                            </label>

										<div className="col-md-6">
											<input className="form-control" data-testid="post-image-pick" type="file" id="formFileMultiple"
												   name="formFileMultiple" multiple required onChange={handleFileRead}/>
											<div className="invalid-feedback">
												Campo requerido
											</div>
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
												value={uniId}
												onChange={e => {setUniId(e.target.value);
																dispatch(actions.findSubjects(e.target.value));
																setSubjectId('');
												}}
												required
											/>
											<div className="invalid-feedback">
												Campo requerido
											</div>

										</div>
									</div>

									<div className="form-group col">
										<label htmlFor="formFileMultiple" className="col-md-12 col-form-label">
											Asignatura
										</label>
										<div className="col-md-6">
											<SubjectSelector
												id="categoryId"
												data-testid="post-category-pick"
												className={`custom-select my-1 mr-sm-2 form-control`}
												value={subjectId}
												onChange={e => setSubjectId(e.target.value)}
												required
												disabled={uniId===''}
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
