import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { useFetch } from '../../hooks/useFetch';
import { Carousel } from './carousel-imp/Carousel';
import { ProductImagesSlider } from './productImagesSlider/ProductImagesSlider'

export const ProductGallery = () => {

    const { id } = useParams()
    const [data] = useFetch(`http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products/${id}`);


    const secondPictures = data && data.images;

    const [modal, setmodal] = useState(false)
    const toggle = () => {
        setmodal(!modal);
    }

    return (
        <>
            <div className='productGallery__container-img'>

                <div className='productGallery__box-main-img'>
                    <img src={data && data.images[0].url} alt="product images" className='productGallery__img-main' />
                </div>
                
                <div className='productGallery__box-gallery'>
                    <span className='productGallery__box-gallery_span' onClick={toggle}>Ver más</span>
                    {
                        secondPictures === null ? <p> No disponible</p> :
                            secondPictures.filter((image, index) => { return index > 0 && index <= 4 })
                                .map((item) => (
                                    <div className='productGallery__box-img' key={item.id}>
                                        <img src={item.url} alt="product images" className='productGallery__img-gallery' />
                                    </div>
                                ))
                    }
                </div>
                <Carousel
                    state={modal}
                    toggle={toggle}
                >

                    <ProductImagesSlider />

                </Carousel>

            </div>
        </>
    )
}