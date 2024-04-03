import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import {useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import * as actions from "../actions";

const UserProfile = () => {

    const dispatch = useDispatch();
    const profile = useSelector(selectors.getUserProfile);
    const navigate = useNavigate();
    const { id } = useParams();

    const navigatetoPostDetail = (id) => {
        const path = `/feed/posts/${id}`;
        navigate(path);
    }

    const navigatetoChat = (id, userName) => {
        const path = `/messages/${id}/${userName}`;
        navigate(path);
    }

    useEffect(() => {

            const userId = Number(id);

            if (!Number.isNaN(id)) {
                dispatch(actions.userProfile(userId))
            }
        }, [id, dispatch]
    )

    const handleFollowClick = (event) => {
        event.preventDefault();

        if(!Number.isNaN(id)) {
            if(profile.followed)
                dispatch(actions.unfollowUser(Number(id)));
            else
                dispatch(actions.followUser(Number(id)));
        }
    };

    if (!profile)
        return null;

    return (
        <>
            <div>{profile.userName}</div>
            <div>{profile.numFollowers}</div>
            <div>{profile.numFollowed}</div>

            <button onClick={e => handleFollowClick(e)}> {profile.followed ? 'Unfollow' : 'Follow'}</button>

            <button onClick={e => navigatetoChat(profile.id, profile.userName)}> Chat </button>

            <div className="row">
                {profile.posts.items &&
                    profile.posts.items.map((post, idx) => (
                        <div key={post.id} className="col-12 col-lg-6 mb-5" data-testid={`post-${idx}`}>
                            <div
                                style={{ marginBottom: '1rem' }}
                                className={`card post-card bg-white rounded-4`}
                                onClick={() => navigatetoPostDetail(post.id)}
                            >
                                <div className="Post_feed">
                                    <div className="post-content" style={{ width: '100%', display: 'block' }}>
                                        <div className="post-text">
                                            <div className="box">
                                                <h2 data-testid={`post-title-${idx}`}>{post.userName}</h2>
                                            </div>
                                            <div className="post-text-info">
                                                <h3 data-testid={`post-academicYear-${idx}`} style={{ color: 'rgba(0,0,0,0.3)', fontSize: '1.5em', margin: 'auto 0' }}>{post.academicYear}</h3>
                                                <h3 data-testid={`post-creationDate-${idx}`}>{post.creationDate}</h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
            </div>
        </>
    );

}

export default UserProfile;