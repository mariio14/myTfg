import { useDispatch, useSelector } from "react-redux";
import * as selectors from "../selectors";
import {useNavigate, useParams} from "react-router-dom";
import * as actions from "../actions";
import React, { useEffect } from 'react';
import { Pager } from "../../common";


const EtiquetaFeed = () => {

    const dispatch = useDispatch();
    const {id} = useParams();
    const feed = useSelector(selectors.getFeed);
    const navigate = useNavigate();
    var text = "Publicaciones con etiqueta";

    useEffect(() => {
        const etiquetaId = Number(id);

        if (!Number.isNaN(etiquetaId)) {
            dispatch(actions.getEtiquetaFeed(etiquetaId, { page: 0 }));
        }

    }, [dispatch]);

    const navigatetoPostDetail = (postId) => {
        const path = `/feed/posts/${postId}`;
        navigate(path);
    }

    if (feed.result == null) {
        return
    } else if (feed.result.items.length === 0) {
        text = "Sin resultados";
    }

    return (
        <div className="container mt-4">

            <h2 style={{ fontSize: '40px', textAlign: 'center', paddingTop: '0.2rem', paddingBottom: '1rem' }}>
                {text}
            </h2>

            <div className="row">
                {feed.result.items &&
                    feed.result.items.map((post, idx) => (
                        <div key={idx} className="col-12 col-lg-6 mb-5" data-testid={`post-${idx}`}>
                            <div
                                style={{ marginBottom: '1rem' }}
                                className={`card post-card 'bg-white' rounded-4`}
                                onClick={() => navigatetoPostDetail(post.id)}>
                                <div className="Post_feed">
                                    <div className="post-content" style={{ width: '100%' , display:'block'}}>
                                        <div className="post-text">
                                            <div className="box">
                                                <h2 data-testid={`post-title-${idx}`}>{post.titulo}</h2>
                                            </div>
                                            <div className="post-text-info">
                                                <h3 data-testid={`post-academicYear-${idx}`} style={{color:'rgba(0,0,0,0.3)', fontSize:'1.5em', margin:'auto 0'}}>{post.academicYear}</h3>
                                                <h3 data-testid={`post-creationDate-${idx}`}>{post.creationDate}</h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
            </div>
            {
                text !== "Sin resultados" && (<Pager
                        back={{
                            enabled: feed.criteria.page >= 1,
                            onClick: () => dispatch(actions.previousFindMyPostsResultPage(feed.criteria))
                        }}
                        next={{
                            enabled: feed.result.existMoreItems,
                            onClick: () => dispatch(actions.nextFindMyPostsResultPage(feed.criteria))
                        }} />
                )
            }
        </div >
    );
}
export default EtiquetaFeed;