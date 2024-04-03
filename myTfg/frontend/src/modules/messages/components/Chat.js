import {useEffect, useState} from "react";
import * as actions from "../actions";
import {useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import * as userSelectors from "../../users/selectors";
import {Errors, Pager} from "../../common";
import {Avatar} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {PostSocket} from "../index";


const Chat = () => {

    const {id,userName} = useParams();
    const dispatch = useDispatch();
    const messages = useSelector(selectors.getMessages);
    const myUserId = useSelector(userSelectors.getUserId);
    const [backendErrors, setBackendErrors] = useState(null);
    const [textoComentario, setTextoComentario] = useState('');

    let text = "";
    let form;

    useEffect( () => {
            const userId = Number(id);
            if(!Number.isNaN(userId)){
                dispatch(actions.getMessages(userId));
            }

            return () => dispatch(actions.clearMessages);

        },[id, dispatch]
    );

    const handleSendMessage = event => {

        event.preventDefault();
        const userId = Number(id);
        if(Number.isNaN(userId)) {
            return;
        }

        if (form.checkValidity()) {
            dispatch(
                actions.sendMessage(
                    userId, textoComentario,
                    messages => dispatch(actions.getMessages(Number(id))),
                    errors => setBackendErrors(errors),
                ));
            setTextoComentario('')
        }
        else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    if (messages == null) {
        return;
    } else if (messages.length === 0) {
        text = "Aún no hay ningún comentario";
    } else {
        text = ""
    }

    return(
        <div>
            <PostSocket id={id}/>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <hr className='hr_titulo'></hr>
            <h2 className='titulo_comentarios'>Chat con {userName}</h2>
            <div>
                <h2 style={{ fontSize: '40px', textAlign: 'center', paddingTop: '0.2rem', paddingBottom: '1rem' }}>
                    {text}
                </h2>
                <div>
                    {messages &&
                        messages.map((message, idxMessage) =>
                            <div key={idxMessage} className="row-12 row-lg-6 mb-5 comment-container"
                                 style={{ marginBottom: '1rem' }}>
                                <div className='user_and_delete_icon_comment_div'>
                                    <div className='username_avatar_icon'>
                                        <div className='avatar_div' data-testid={`avatar-comment-${idxMessage}`}>
                                            <Avatar alt="Remy Sharp" src={"data:image/jpeg;base64," + message.avatar} sx={{ width: 45, height: 45 }}/>
                                        </div>
                                        <p className="comment-username" data-testid={`userName-comment-${idxMessage}`}>{message.date}</p>
                                    </div>
                                </div>
                                <h5 className="comment-text" data-testid={`text-comment-${idxMessage}`}>{message.text}</h5>
                                <hr/>
                            </div>
                        )}
                </div>
            </div>
            <form ref={node => form = node}
                  className="needs-validation" noValidate
                  onSubmit={e => handleSendMessage(e)}>
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
                        Enviar
                    </button>
                </div>
            </form>
        </div>
    );
}
export default Chat;