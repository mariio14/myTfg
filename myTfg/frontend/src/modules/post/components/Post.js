import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';
import {AcademicYearSelector, UniversitySelector, SubjectSelector} from "../index";
import 'react-datepicker/dist/react-datepicker.css';
import * as selector from "../selectors";
import {Avatar} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import SvgIcon from "@mui/material/SvgIcon";

const Post = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
	const [academicYear, setAcademicYear] = useState('');
	const [uniId, setUniId] = useState('');
	const [subjectId, setSubjectId] = useState('');
	const [files, setFiles] = useState([]);
	const [etiquetas, setEtiquetas] = useState([]);
	const [mode, setMode] = useState('Añadir');
	const [selectedKey, setSelectedKey] = useState('');
	const [selectedValue, setSelectedValue] = useState('');
	const [backendErrors, setBackendErrors] = useState(null);
	const keys = useSelector(selector.getKeys);
	const values = useSelector(selector.getValues);
    
    let form;

	useEffect( () => {
		dispatch(actions.findAllKeys());
		},[]
	);

	const handleFileRead = (event) => {
		setFiles([...event.target.files]);
	}

	const handleAddEtiqueta = () => {
		let text = selectedKey + "-" + selectedValue
		if (!etiquetas.includes(text)) {
			setEtiquetas([...etiquetas, text]);
		}
	}

	const handleDelete = (event, etiqueta) => {
		setEtiquetas(etiquetas.filter(e => e !== etiqueta));
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
			//formData.append('etiquetas', etiquetas);
			etiquetas.forEach((etiqueta, index) => {
				formData.append('etiquetas', etiqueta);
			});

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

	const CloseIcon = (props) => (
		<SvgIcon {...props}>
			<path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
		</SvgIcon>
	);

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
											<UniversitySelector
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

									<form>
									<div className="form-group col">
										<label htmlFor="formFileMultiple" className="col-md-12 col-form-label">
											Añade etiquetas
										</label>
										<h5 className="card-header bg-black">
											<div className="btn-group" role="group">
												<button
													className={`btn btn-dark ${mode === 'Añadir' ? 'active' : ''}`}
													onClick={() => {setMode('Añadir');
														setSelectedKey('');
														setSelectedValue('')}}
													data-testid="post-button-product">
													Elegir existente
												</button>
												<button
													className={`btn btn-dark ${mode === 'Crear' ? 'active' : ''}`}
													onClick={() => {setMode('Crear');
														setSelectedKey('');
														setSelectedValue('')}}
													data-testid="post-button-cupon">
													Crear
												</button>
											</div>
										</h5>
										{mode === 'Añadir' && (
											<div className="form-group col">
												<select id="etiquetaAnadir"
														data-testid="post-category-pick"
														className={`custom-select my-1 mr-sm-2 form-control`}
														value={selectedKey}
														onChange={e => {setSelectedKey(e.target.value);
															dispatch(actions.findValues(e.target.value));
															setSelectedValue('');
														}}
														required>

													<option  value="" disabled>Selecciona un key</option>

													{keys && keys.map(key =>
														<option key={key} value={key}>{key}</option>
													)}

												</select>
												<div> - </div>
												<select id="etiquetaAnadirValue"
														data-testid="post-category-pick"
														className={`custom-select my-1 mr-sm-2 form-control`}
														value={selectedValue}
														onChange={e => setSelectedValue(e.target.value)}
														required
														disabled={selectedKey===''}>

													<option  value="" disabled>Selecciona un value</option>

													{values && values.map(value =>
														<option key={value} value={value}>{value}</option>
													)}

												</select>

											</div>
										)}
										{mode === 'Crear' && (
											<div className="form-group col">
												<div className="col-md-6">
													<input type="text" id="titulo" className="form-control"
														   value={selectedKey}
														   data-testid="post-input-title"
														   onChange={e => setSelectedKey(e.target.value)}
														   autoFocus
														   required/>
													<div className="invalid-feedback">
														Campo requerido
													</div>
												</div>

												<div> - </div>

												<div className="col-md-6">
													<input type="text" id="titulo" className="form-control"
														value={selectedValue}
														data-testid="post-input-title"
														onChange={e => setSelectedValue(e.target.value)}
														autoFocus
														required/>
													<div className="invalid-feedback">
														Campo requerido
													</div>
												</div>
											</div>
										)}

										<button onClick={() => handleAddEtiqueta()} className="btn btn-primary btn-dark" id="loginButton" data-testid="post-button-publicar">
											Añadir etiqueta
										</button>

									</div>
									</form>

									<div>
										{etiquetas && etiquetas.map((etiqueta, idx) => (
											<div key={idx} style={{margin:'2em'}}>
												<div className={`card post-card 'bg-lightblue'} rounded-4`}
													 style={{ display: 'flex', flexDirection: 'row', minHeight:'50px'}}>
													<div className="post-content" style={{width: '100%', display:'block', margin:'auto 0px'}}>
														<div className="post-text">
															<div className="box" style={{display:'flex', margin:'auto 0'}}>
																<h2 style={{borderBottom:'none'}} data-testid={`notification-text-${idx}`}>
																	{etiqueta}
																</h2>
															</div>
														</div>
													</div>
													<IconButton data-testid={`notification-button-${idx}`} style={{height:'40px', margin:'auto 0'}}  onClick={(e) => handleDelete(e, etiqueta)}>
														<CloseIcon />
													</IconButton>
												</div>
											</div>
										))}
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
