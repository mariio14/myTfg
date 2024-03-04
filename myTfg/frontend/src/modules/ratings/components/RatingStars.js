import {useDispatch} from "react-redux";
import React from "react";
import * as actions from '../actions';
import Rating from '@mui/material/Rating';
import {useParams} from "react-router-dom";
import IconButton from '@mui/material/IconButton';
import SvgIcon from '@mui/material/SvgIcon';

const RatingStars = ({ rating }) => {

    const {id} = useParams();
    const dispatch = useDispatch();

    const handleRate = (event, rating) => {

        event.preventDefault();

        if(!Number.isNaN(id)) {
            dispatch(actions.valorar(
                Number(id),
                rating,
            ));
        }
    }

    const handleClick = (event) => {
        event.preventDefault();

        if(!Number.isNaN(id)) {
            dispatch(actions.deleteRating(rating.id, id));
        }
    }

    const CloseIcon = (props) => (
        <SvgIcon {...props}>
            <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
        </SvgIcon>
    );

    if(rating==null)
        return null;

    return(
        <div style={{ display: 'flex', alignItems: 'center' }}>
            <Rating
                name="post-rating"
                value={rating.rating}
                precision={1.0}
                onChange={(e,newValue) => handleRate(e, newValue)}
                style={{ marginRight: '10px' }}
                data-testid="ratingStars-stars"
            />
            <IconButton onClick={(e) => handleClick(e)} data-testid="ratingStars-close-button">
                <CloseIcon />
            </IconButton>
        </div>
    )
}
export default RatingStars;