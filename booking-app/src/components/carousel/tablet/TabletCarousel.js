import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/scss";
import "swiper/scss/pagination";
import { Autoplay, Pagination } from "swiper";
import { useParams } from "react-router-dom";
import { useFetch } from "../../../hooks/useFetch";

export const TabletCarousel = () => {

    const { id } = useParams()
    const [data] = useFetch(`http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products/${id}`);

    const pictures = data && data.images;

    return (
        <div className='tabletCarousel__container-img'>
            <Swiper
                modules={[Autoplay, Pagination]}
                slidesPerView={1}
                pagination={{
                    type: 'fraction',
                }}
                autoplay={{
                    delay: 3000,
                    disableOnInteraction: false,
                }}
                className="tabletCarousel__images"
            >
                {
                    pictures === null ? 'No disponibles' :
                        pictures.map((item) => (
                            <SwiperSlide key={item.id}>
                                <img src={item.url} alt="product images" />
                            </SwiperSlide>
                        ))
                }
            </Swiper>
        </div>
    )
}
