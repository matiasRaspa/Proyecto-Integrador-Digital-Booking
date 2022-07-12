import React from 'react'
import { useData } from '../../hooks/useData';
import { useFetch } from '../../hooks/useFetch';
import Map from '../map/Map'

export const ViewMap = ({ state, setState, productId }) => {

    let id = productId ? productId : 1
    const [data] = useFetch(`http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products/${id}`);
    const [name, latitude, longitude] = useData(data);

    const viewMap = () => {
        setState(false);
    }

    return (
        <div>
            <>
                {state &&
                    <div className='viewMap__container'>
                        <div onClick={viewMap} className='background-map' />
                        <div className='box-main'>
                            <div>
                                <span className='close-button' onClick={viewMap}>X</span>
                            </div>
                            <Map name={name} latitude={latitude} longitude={longitude} height="100%" />
                        </div>
                    </div>
                }
            </>
        </div>
    )
}
