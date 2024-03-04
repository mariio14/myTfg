import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import { ImageUploading } from "react-images-uploading";

import * as actions from '../actions';

const Post = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [categoria, setCategoria] = useState('');
    const [url, setUrl] = useState('');
	const [precio, setPrecio] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const [images, setImages] = useState([]);
    
    let form;


	const handleFileRead =  async (event) => {
		const file = event.target.files;
		const base64 = [];
		for(let i=0; i<file.length; i++){
			base64.push(await convertBase64(file[i]));

		}
		setImages(base64);
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
				images,
                titulo,
                descripcion,
                categoria,
                url,
                precio,
                () => navigate('/users/createPost/postCompleto'),
                errors => setBackendErrors(errors),
                () => {
                    navigate('/');
                }));

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
			                        <div className="form-group row">
			                            <label htmlFor="title" className="col-md-3 col-form-label">
			                                Titulo
			                            </label>
			                            <div className="col-md-4">
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
			                         <div className="form-group row">
			                            <label htmlFor="descripcion" className="col-md-3 col-form-label">
			                                Descripcion
			                            </label>
			                            <div className="col-md-4">
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
			                         <div className="form-group row">
			                            <label htmlFor="categoria" className="col-md-3 col-form-label">
			                                Categoria
			                            </label>
			                            <div className="col-md-4">
			                                <input type="text" id="categoria" className="form-control"
			                                    value={categoria}
			                                    onChange={e => setCategoria(e.target.value)}
			                                    autoFocus
			                                    required/>
			                                <div className="invalid-feedback">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
									<div className="form-group row">
										<label htmlFor="url" className="col-md-3 col-form-label">
											Url
										</label>
										<div className="col-md-4">
											<input type="text" id="url" className="form-control"
												   value={url}
												   onChange={e => setUrl(e.target.value)}
												   required/>
											<div className="invalid-feedback">
												Campo requerido
											</div>
										</div>
									</div>
			                        <div className="form-group row">
			                            <label htmlFor="password" className="col-md-3 col-form-label">
			                                Precio
			                            </label>
			                            <div className="col-md-4">
			                                <input type="number" id="number" className="form-control"
			                                    value={precio}
			                                    onChange={e => setPrecio(e.target.value)}
			                                    min="1"
			                                    required/>
			                                <div className="invalid-feedback">
			                                    Campo requerido
			                                </div>
			                            </div>
			                        </div>
			                        <div className="form-group row">
			                            <div className="offset-md-3 col-md-1">
			                                <button type="submit" className="btn btn-primary" id="loginButton">
			                                    Post
			                                </button>
			                            </div>             
			                        </div>
			                       
			                    </form>
								<div className="mb-3">
									<label htmlFor="formFileMultiple" className="form-label"/>
									<input data-testid="select-images-component" className="form-control" type="file" id="formFileMultiple" multiple accept="image/*" onChange={handleFileRead}/>
								</div>
			                </div>
			            </div>
					</div>
				</div>
            </div>
        </div>       
    );

}

export default Post;
