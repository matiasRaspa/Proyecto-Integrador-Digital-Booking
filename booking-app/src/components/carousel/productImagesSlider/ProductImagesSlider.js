import React, { useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { useFetch } from '../../../hooks/useFetch';
import { useParams } from 'react-router-dom';

// Import Swiper styles
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/thumbs";
import "swiper/css/pagination";

// import required modules
import { Navigation, Thumbs, Pagination } from "swiper";

export const ProductImagesSlider = () => {
  const { id } = useParams();
  const [data] = useFetch(`http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products/${id}`);
  const [thumbsSwiper, setThumbsSwiper] = useState();

  return (
    <>
      <Swiper
        modules={[Navigation, Thumbs, Pagination]}
        navigation={true}
        slidesPerView={1}
        initialSlide={0}
        thumbs={{ swiper: thumbsSwiper && !thumbsSwiper.destroyed ? thumbsSwiper : null }}
        pagination={{
          type: 'fraction',
        }}
        className="productImagesSlider__galleryMain"
      >
        {data && data.images.map((item) => (
          <SwiperSlide key={item.id} >
            <img src={item.url} alt="product images" />
          </SwiperSlide>
        )
        )}
      </Swiper>

      <div className='productImagesSlider__box-gallery-mini'>
        <Swiper
          modules={[Navigation, Thumbs]}
          onSwiper={setThumbsSwiper}
          spaceBetween={15}
          slidesPerView={4}
          className="productImagesSlider__galleryMini"
        >
          {data && data.images.map((item) => (
            <SwiperSlide key={item.id} >
              <img src={item.url} alt="imagenes" />
            </SwiperSlide>
          )
          )}
        </Swiper>
      </div>
    </>
  )
}
