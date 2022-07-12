import React, { useContext, useEffect, useState } from "react";
import { UserContext } from '../../UserContext'
import { LikeContainer } from "./LikeStyled";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faLocationDot } from '@fortawesome/free-solid-svg-icons';
import '../../styles/components/_like.scss';
import LikeModal from './LikeModal';

export default function Like({ id }) {
    const { auth } = useContext(UserContext);
    const { favourites, setFavourites } = useContext(UserContext);
    const [stateModal, setStateModal] = useState(false);
    const [liked, setLiked] = useState(false);

    useEffect(() => {

        if (!auth && favourites.length > 0) {
            deSelectFavourite(favourites, id);
        } else if (favourites.includes(parseInt(id))) {
            document.getElementById("fav-" + id).classList.add('body__heart_selected');
        }
    });

    const handlerCheck = (id) => {
        if (auth) {
            selectFavourite(id);
        } else {
            setStateModal(true);
        }
    }

    const selectFavourite = (id) => {
        if (!liked) {
            document.getElementById("fav-" + id).classList.add('body__heart_selected');
            setLiked(true);
            favourites.push(parseInt(id));

        } else {
            deSelectFavourite(favourites, id);
            setLiked(false);

        }
    }

    function deSelectFavourite(favourites, id) {
        document.getElementById("fav-" + id).classList.remove('body__heart_selected');
        arrayRemove(favourites, parseInt(id));
    }

    function arrayRemove(arr, value) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] === value) {
                arr.splice(i, 1);
                i--;
            }
        }
    }

    return (
        <LikeContainer id="1" location="test">
            <section className="like_seccion1">
                <FontAwesomeIcon id={"fav-" + id} className='body__heart' icon={faHeart} onClick={() => { handlerCheck(id) }} />
            </section>
            <LikeModal stateModal={stateModal} setStateModal={setStateModal} />
        </LikeContainer>
    );
}