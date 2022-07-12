import React from 'react';
import {ProductImagesSlider} from '../productImagesSlider/ProductImagesSlider'
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/thumbs';
import 'swiper/css/pagination';

export const Carousel = ({state, toggle }) => {

    return (
        <>
            {state &&
                <div className='carousel__container'>
                    <div onClick={toggle} className='carousel__background-gallery' />
                    <div className='carousel__box-main'>
                        <div>
                            <span className='carousel__close-button' onClick={toggle}>X</span>
                        </div>
                        <ProductImagesSlider />
                    </div>
                </div>
            }
        </>
    )

}


