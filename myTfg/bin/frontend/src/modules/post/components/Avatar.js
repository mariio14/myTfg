import { useSelector } from 'react-redux';
import {useState} from 'react';
import {useDispatch} from 'react-redux';





const Avatar = () => {
	const [images, setImages] = useState('');
	
	return(
		<div className="container mt-4">
			    <div className="row justify-content-center">
			        <div className="col-md-6">
			            <div className="card bg-light border-dark">
			                <h5 className="card-header">
			                    Cambiar Avatar
			                </h5>
			                <div className="card-body" style={{padding:'10px', margin:'1em auto', textAlign:'center'}}>
				                <input
					   				type="file"
					   				id="imageFile"
					   				className="form-control-file"
					  				onChange={e => setImages(e.target.files[0])}
					   				accept="image/*"
					   			/>
								<div className="invalid-feedback">
									Sube una imagen válida.
								</div>
								<div style={{margin:'1em auto'}}>
									<button type="submit" className="btn btn-primary btn-dark" style={{width:'100px'}} id="loginButton">
										Guardar
									</button>
								</div>
							</div>
			            </div>
			        </div>
			    </div>
		</div>
	)
}
export default Avatar;