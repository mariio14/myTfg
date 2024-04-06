import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import { useParams } from "react-router-dom";

import * as actions from '../actions';
import * as selectors from "../selectors";
import DatePicker from "react-datepicker";
import {UniversitySelector} from "../index";

const UpdatePost = () => {
	
	const { id } = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();
	const post = useSelector(selectors.getPost);
	const [idd, setIdd] = useState(0);
    const [titulo, setTitulo] = useState();
    const [descripcion, setDescripcion] = useState();
    const [subjectId, setSubjectId] = useState();
    const [academicYear, setAcademicYear] = useState();
	const [backendErrors, setBackendErrors] = useState();

    let form;

	useEffect(() => {

			const postId = Number(id);

			if (!Number.isNaN(id) && postId !== idd) {
				dispatch(actions.findPostbyId(postId))
				setIdd(postId)
			}

            setTitulo(post.titulo);
            setSubjectId(post.subjectId);
            setDescripcion(post.descripcion);
            setAcademicYear(post.academicYear);

			return () => dispatch(actions.postByIdClear);
		}, [id, dispatch, post.titulo, post.subjectId, post.descripcion, post.id, post.academicYear, idd]
	)


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
            dispatch(actions.updatear(
				id,
                titulo,
                descripcion,
                subjectId,
                academicYear,
                () => navigate('/users/myFeed'),
                () => setBackendErrors("Usuario no encontrado"),
                ));

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
								Actualizar post
							</h5>
			                <div className="card-body">
			                    <form ref={node => form = node} 
			                        className="needs-validation" noValidate 
			                        onSubmit={e => handleSubmit(e)}>
			                        <div className="form-group col">
			                            <label htmlFor="title" className="col-md-12 col-form-label">
			                                Titulo
			                            </label>
			                            <div data-testid="titulo" className="col-md-6">
			                                <input type="text" id="titulo" className="form-control"
			                                    value={titulo}
			                                    onChange={e => setTitulo(e.target.value)}
			                                    autoFocus
			                                    required/>
			                                <div className="invalid-feedback">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                         <div className="form-group col" data-testid="descripcion">
			                            <label htmlFor="descripcion" className="col-md-12 col-form-label">
			                                Descripcion
			                            </label>
			                            <div className="col-md-12">
			                                <input type="text" id="descripcion" className="form-control"
			                                    value={descripcion}
			                                    onChange={e => setDescripcion(e.target.value)}
			                                    autoFocus
			                                    required/>
			                                <div className="invalid-feedback">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                         
									<div className="form-group col" data-testid="url">
										<label htmlFor="url" className="col-md-12 col-form-label">
											AcademicYear
										</label>
										<div className="col-md-12">
											<input type="text" id="url" className="form-control"
												   value={academicYear}
												   onChange={e => setAcademicYear(e.target.value)}
												   required/>
											<div className="invalid-feedback">
												Campo requerido
											</div>
										</div>
									</div>
									<div className="form-group col" data-testid="post-category-pick">
										<label htmlFor="formFileMultiple" className="col-md-12 col-form-label">
											Subject
										</label>
										<div className="col-md-6">
											<UniversitySelector
												id="categoryId"
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
									<div className="form-group col" data-testid="Editar Post">
			                            <div className="col-md-1">
			                            	<br/>
			                                <button type="submit" className="btn btn-primary btn-dark" id="loginButton">
			                                    Editar Post
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

export default UpdatePost;
