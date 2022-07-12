import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBinoculars } from '@fortawesome/free-solid-svg-icons';
import { useFetch } from '../../hooks/useFetch';
import { BarComeHome } from '../bar-come-home/BarComeHome';
import { Card } from '../card/Card';
import ModalPortal from '../modals/ModalPortal';
import { ViewMap } from '../modals/ViewMap';

export const MyBookings = () => {

    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    const [productId, setProductId] = useState(null);
    const [state, setState] = useState(false)
    const { userId } = useParams();
    const [data] = useFetch(`http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/bookingDetails/UserId=${userId}`);
    const message = 'No disponible'

    return (
        <main className='myBookings__container'>

            <ModalPortal>
                <ViewMap state={state} setState={setState} productId={productId} />
            </ModalPortal>

            <BarComeHome title={'Mis Reservas'} />
            {
                data === null || typeof data === 'string'?
                    <div className='myBookings__reserveoff'>
                        <FontAwesomeIcon className='myBookings__boniculars' icon={faBinoculars} />

                        <Link to={"/"}>
                            <p className='myBookings__to-home'>Volver a home</p>
                        </Link>
                        <h2>Aún no has efectuado ninguna reserva</h2>
                    </div>
                    :
                    <div className='body__recommendation'>
                        <section className=' body__section info'>
                            {
                                data && data.map((reserve) => {
                                    return (

                                        <div key={reserve.id} className='body__card recommendation respo'>

                                            <Card
                                                id={reserve.product.id !== null ? reserve.product.id : message}
                                                image={reserve.product.image !== null ? reserve.product.image.url : message}
                                                category={reserve.product.category !== null ? reserve.product.category : message}
                                                stars={reserve.product.stars !== null ? reserve.product.stars : message}
                                                name={reserve.product.name !== null ? reserve.product.name : message}
                                                score={reserve.product.score !== null ? reserve.product.score : message}
                                                features={reserve.product.features !== null ? reserve.product.features : message}
                                                description={reserve.product.description !== null ? reserve.product.description : message}
                                                startDate={reserve.startDate}
                                                endDate={reserve.endDate}
                                                hour={reserve.eta}
                                                setProductId={setProductId}
                                                setState={setState}
                                            />

                                        </div>
                                    )

                                })}

                        </section>
                    </div>
            }

        </main>
    )
}
