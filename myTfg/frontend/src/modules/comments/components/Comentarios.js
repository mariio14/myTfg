import './styleComments.css';
import {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useParams} from "react-router-dom";
import * as selectors from "../selectors";
import * as actions from '../actions';
import { Pager } from "../../common";
import IconButton from '@mui/material/IconButton';
import SvgIcon from '@mui/material/SvgIcon';
import * as userSelectors from "../../users/selectors"
import {Errors} from "../../common";
import { Avatar } from '@mui/material';


const Comentarios = () => {
	
	const {id} = useParams();
	const dispatch = useDispatch();
    const comentariosBlock = useSelector(selectors.getCommentsAndAnswersFromPost);
    const userId = useSelector(userSelectors.getUserId);
    const [textoComentario, setTextoComentario] = useState('');
    const [answerText, setAnswerText] = useState('');
    const [dialogOpen, setDialogOpen] = useState(null);
    const [backendErrors, setBackendErrors] = useState(null);
    
    let text = "";
    let form;
    
    useEffect( () => {
			const postId = Number(id);
			if(!Number.isNaN(postId)){
				dispatch(actions.getPostCommentsAndAnswersBlock({postId,  page: 0 }));
			}
			
		return () => dispatch(actions.clearCommentsAndAnswers);
		
		},[id, dispatch]
	);
	
	const handlePostComment = event => {

        event.preventDefault();
        
        const postId = Number(id);
        if(Number.isNaN(id)) {
			return;
		}
        
		
        if (form.checkValidity()) {
            dispatch(
				actions.uploadComment(
				postId,
				textoComentario,
				comment => dispatch(actions.getPostCommentsAndAnswersBlock({postId,  page: 0 })),
                errors => setBackendErrors(errors),
       			));
			setTextoComentario('')
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }
	
	const handleDelete = (event, commentId) => {
		
		event.preventDefault();
		if(!Number.isNaN(commentId)){
			dispatch(actions.deleteComment
			(commentId, Number(id)));
        }
	}
	
	const handleCreateAnswer = (event, commentId, respuestaId) => {
		
		event.preventDefault();
		const postId = Number(id);
		if(!Number.isNaN(commentId)){
			dispatch(actions.uploadAnswer(
				commentId,
				answerText,
				respuestaId,
				comment => dispatch(actions.getPostCommentsAndAnswersBlock({postId,  page: 0 })),
				errors => setBackendErrors(errors),
				));
		}
		setDialogOpen(null);
	}
	
	
	 const CloseIcon = (props) => (
        <SvgIcon {...props}>
            <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
        </SvgIcon>
    );
    
    const isUserComment = (userIdOfComment) => {
		return userId === userIdOfComment;
	}
	
	
	
	if (comentariosBlock.result == null) {
		return
	} else if (comentariosBlock.result.items.length === 0) {
		text = "Aún no hay ningún comentario";
	} else {
		text = ""
	} 
	
	
    
	
	return (
			<div>
			 <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
    			<hr className='hr_titulo'></hr>
    			<h2 className='titulo_comentarios'>COMENTARIOS</h2>
    			<form ref={node => form = node} 
			                        className="needs-validation" noValidate 
			                        onSubmit={e => handlePostComment(e)}>
	    			<div className='Add_comment_box'>
	    				<input type="text" id="textoComentario" className="form-control" data-testid="comment-input"
						value={textoComentario}
						onChange={e => setTextoComentario(e.target.value)}
						autoFocus
						required
						style={{ padding: '20px', border: '1px solid #ddd', borderRadius: '5px' }}
						/>
						<br/>
						<button 
						onSubmit
						type="submit"
						data-testid="comment-button"
						className="comentario-button"
						id="loginButton" style={{ display: "inline-block" }}> 
							 Publicar
						</button>
	    			</div>
	    		</form>	
    			<div>
			<h2 style={{ fontSize: '40px', textAlign: 'center', paddingTop: '0.2rem', paddingBottom: '1rem' }}>
				{text}
			</h2>
				<div>
				{comentariosBlock.result.items  &&
				 comentariosBlock.result.items.map((commentsAndAnswersBlock, idxComment) => 
					<div key={idxComment} className="row-12 row-lg-6 mb-5 comment-container" 
					style={{ marginBottom: '1rem' }}>
							<div className='user_and_delete_icon_comment_div'>
								<div className='username_avatar_icon'>
									<div className='avatar_div' data-testid={`avatar-comment-${idxComment}`}>
										<Avatar alt="Remy Sharp" src={"data:image/jpeg;base64," + commentsAndAnswersBlock.comentario.avatar} sx={{ width: 45, height: 45 }}/>
									</div>
									<h5 className="comment-username" data-testid={`userName-comment-${idxComment}`}>{commentsAndAnswersBlock.comentario.userName}</h5>
								</div>
								{isUserComment(commentsAndAnswersBlock.comentario.userId) && <IconButton onClick={(e) => handleDelete(e, commentsAndAnswersBlock.comentario.id)} data-testid={`closeButton-comment-${idxComment}`}>
                				<CloseIcon />
            					</IconButton>}
							</div>
							<p className="comment-text" data-testid={`text-comment-${idxComment}`}>{commentsAndAnswersBlock.comentario.textoComentario}</p>
            				<div className="comment-reply-button-container">
							  <button onClick={() => setDialogOpen(commentsAndAnswersBlock.comentario.id)} className="comment-reply-button" data-testid={`responder-button-comment-${idxComment}`}>
							    Responder comentario
							  </button>
							</div>
            				
            				
							{dialogOpen === commentsAndAnswersBlock.comentario.id && (
							  <div className="answer-container">
							    <div className="textarea-container">
							      <textarea
									data-testid={`answer-input-${idxComment}`}
							        onChange={e => setAnswerText(e.target.value)}
							        id="respuesta" 
							        placeholder="Escribe tu respuesta aquí..." 
							      ></textarea>
							    </div>
							    <IconButton onClick={() => setDialogOpen(null)}>
	                				<CloseIcon />
	            				</IconButton>
							    <div className='send_answer_button_div'>
								    <button
									  data-testid={`enviar-respuesta-button-${idxComment}`}
								      onClick={(e) => handleCreateAnswer(e, commentsAndAnswersBlock.comentario.id, 0)} 
								      className="answer-submit-button"
								      style={{ float: 'right', marginTop: '10px' }}
								    >
								      Enviar respuesta
								    </button>
								</div>    
							  </div>
							)}
							
            				<h5 style = {{marginTop: '20px'}}>Respuestas</h5>
            				<hr/>
							{commentsAndAnswersBlock.respuestas.map((respuesta, idxAnswer) =>
						
							<div key={idxAnswer} className="row-12 row-lg-6 mb-5 comment-container answer" id = "container-answer" style={{ marginBottom: '1rem' }}>
								<div className='user_and_delete_icon_comment_div'>
									<div className='username_avatar_icon'>
										<div className='avatar_div' data-testid={`avatar-answer-${idxComment}-${idxAnswer}`}>
											<Avatar alt="Remy Sharp" src={"data:image/jpeg;base64," + respuesta.avatar} 
											sx={{ width: 50, height: 50}}/>
										</div>
										<h5 className="comment-username" data-testid={`answer-userName-${idxComment}-${idxAnswer}`}>{respuesta.userName}</h5>
									</div>
									{isUserComment(respuesta.userId) && <IconButton onClick={(e) => handleDelete(e, respuesta.id)}>
	                				<CloseIcon />
	            				</IconButton>}
								</div>	
								<p className="comment-text" data-testid={`answer-text-${idxComment}-${idxAnswer}`}>{respuesta.textoComentario}</p>
								<div className="comment-reply-button-container">
									<button onClick={() => setDialogOpen(respuesta.id)} className="comment-reply-button" data-testid={`answer-respuesta-${idxComment}-${idxAnswer}`}>
										Responder comentario
									</button>
								</div>

								{dialogOpen === respuesta.id && (
									<div className="answer-container">
										<div className="textarea-container">
										<textarea
											data-testid={`answer-of-answer-input-${idxComment}-${idxAnswer}`}
											onChange={e => setAnswerText(e.target.value)}
											id="respuesta"
											placeholder="Escribe tu respuesta aquí..."
										></textarea>
										</div>
										<IconButton onClick={() => setDialogOpen(null)}>
											<CloseIcon />
										</IconButton>
										<div className='send_answer_button_div'>
											<button onClick={(e) => handleCreateAnswer(e, commentsAndAnswersBlock.comentario.id, respuesta.id)}
													className="answer-submit-button"
													data-testid={`send-answer-answer-${idxComment}-${idxAnswer}`}
													style={{ float: 'right', marginTop: '10px' }}>
												Enviar respuesta
											</button>
										</div>
									</div>
								)}

							</div>

							)}
					</div>
				)}
				</div>
					{
					text !== "Aún no hay ningún comentario" && (<Pager data-testid={"pager-comments"}
						back={{
							enabled: comentariosBlock.criteria.page >= 1,
							onClick: () => dispatch(actions.previousPostCommentsResultPage(comentariosBlock.criteria))
						}}
						next={{
							enabled: comentariosBlock.result.existMoreItems,
							onClick: () => dispatch(actions.nextPostCommentsResultPage(comentariosBlock.criteria))
						}} />
					)
				}
			</div>
		    </div>
	);
}

export default Comentarios;
