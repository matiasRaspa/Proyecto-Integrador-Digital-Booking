import React, { Fragment } from 'react'
import { Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLocationDot } from '@fortawesome/free-solid-svg-icons';
import { getDescriptionScore } from '../../helper/getDescriptionScore';
import { getStarNum } from '../../helper/getStarNum';
import { getIcons } from '../../helper/getIcons'
import Like from '../like/Like';

export const Card = ({ id, image, category, stars, name, score, features, description, startDate, endDate, hour, setProductId, setState }) => {

    const viewMap = (viewId) => {
        setState(true);
        setProductId(viewId)
    }


    return (
        <>

            <div className='body__left '>
                    <Like id={id} />
                <img className='body__img-recommendation' src={image} alt="hotel" />
            </div>

            <div className='body__information'>

                <div className='body__container-desp'>
                    <div >
                        <span className='body__score'>{category}</span>

                        {getStarNum(stars)}

                        <h3>{name}</h3>
                    </div>
                    <div >
                        <div className='body__number'>{score}</div>
                        <p className='body__qly' >{getDescriptionScore(score)}</p>
                    </div>
                </div>

                <div>
                    <FontAwesomeIcon icon={faLocationDot} />
                    <span > A {Math.floor(Math.random() * 1000)} m del centro</span>
                    <span className='body__map' onClick={() => { viewMap(id) }}> MOSTRAR EN EL MAPA  </span>
                    <div>
                        {
                            features.map((element, i) => {
                                return (
                                    <Fragment key={i}>
                                        {getIcons(element, "body__icon-wifi")}
                                    </Fragment>

                                )
                            })
                        }
                    </div>
                </div>

                <p className='body__text'>
                    {
                        startDate === undefined ?
                            '' :
                            <>
                                Fecha Reserva: {startDate} / {endDate}
                                <br />
                                Hora Check-In: {hour}
                                <br />
                            </>
                    }
                    {description}
                </p>

                <Link to={`/product/${id}`}>
                    <button className='body__btn-information'>ver más</button>
                </Link>


            </div>

        </>
    )
}
