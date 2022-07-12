import React, { useContext, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useMediaQuery } from "react-responsive";
import { faLocationDot, faHeart } from "@fortawesome/free-solid-svg-icons";
import { useFetch } from "../../hooks/useFetch";
import Calendar from "./Calendar";
import Map from "../map/Map";
import SocialMediaShare from "../socialMedia/SocialMediaShare";
import { ProductGallery } from "../carousel/ProductGallery";
import { TabletCarousel } from "../carousel/tablet/TabletCarousel";
import { useData } from "../../hooks/useData";
import { UserContext } from "../../UserContext";
import { BarComeHome } from "../bar-come-home/BarComeHome";
import { getDescriptionScore } from "../../helper/getDescriptionScore";
import { getStarNum } from "../../helper/getStarNum";
import { getIcons } from "../../helper/getIcons";
import Like from '../like/Like';

export const Product = () => {
  const { id } = useParams();

  const { auth } = useContext(UserContext);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const [data] = useFetch(
    `http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products/${id}`
  );

  const category = data && data.category.title;
  const nameProduct = data && data.name;
  const location = data && data.location.fullName;
  const locationName = data && data.location.name;
  const score = data && data.score;
  const description = data && data.description;
  const features = data && data.features;
  const politics = data && data.politics;

  const starNum = data && data.stars;

  const validateTheUserIsLoggedIn = auth
    ? `/product/${data && data.id}/booking`
    : "/login?showAlert=true";
  const [name, latitude, longitude] = useData(data);

  // **** Responsive **** //

  const Desktop = ({ children }) => {
    const isDesktop = useMediaQuery({ minWidth: 769 });
    return isDesktop ? children : null;
  };

  const TabletOrMobile = ({ children }) => {
    const isTabletOrMobile = useMediaQuery({ maxWidth: 768 });
    return isTabletOrMobile ? children : null;
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <div>
        <BarComeHome category={category} title={nameProduct} />
        <section className="product_seccion2">
          <div>
            <p className="product_location">
              <FontAwesomeIcon
                className="product_location_icon"
                icon={faLocationDot}
              />
              {location}
            </p>
          </div>
          <div className="product_div_container_stars">
            <div>
              <p className="product_location_qualification">
                {getDescriptionScore(score)}
              </p>

              {getStarNum(starNum)}

            </div>
            <div className="product_location_number">
              <div className="body__number">{score}</div>
            </div>
          </div>
        </section>
        <section className="social_media_container">
        <section className="product_seccion3">
          <SocialMediaShare />
        </section>
        <div className="product_seccion4">
          <Like id={id} />
        </div>
        </section>
        <section>
          <Desktop>
            <ProductGallery />
          </Desktop>

          <TabletOrMobile>
            <TabletCarousel />
          </TabletOrMobile>
        </section>
      </div>
      <div className="product_location_description">
        <h2 className="product_paddingtop">
          Alójate en el corazón de {locationName}
        </h2>
        <p className="product_description">{description}</p>
        <h2 className="product_paddingtop">¿Qué ofrece este lugar?</h2>
        <hr className="product_separador"></hr>
        <div className="product_caracteristicas">
          {

            features === null ? <p> No disponible</p> :
              features.map((element) => {
                return (
                  <div key={element.id} className="product_div_icono_descripcion">

                    {getIcons(element, "product__icon")}

                    <h4 className="product_h4_paddingleft">{element.title}</h4>
                  </div>
                );
              })
          }
        </div>
      </div>
      <div className="product_paddingtop">
        <div className="product_container_fechas">
          <div className="product_location_description">
            <h2 className="product_fechas">Fechas disponibles</h2>
            <div className="product_container_calendar">
              <section className="product_section_calendar_container">
                <div className="product_div_calendar_container">
                  <Calendar />
                </div>
              </section>
              <div className="product_boton_container_reserva">
                <h4>Agregá tus fechas de viaje para obtener precios exactos</h4>
                <Link to={validateTheUserIsLoggedIn}>
                  <button className="product_btn_reserva">
                    Iniciar reserva
                  </button>
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Map name={name} latitude={latitude} longitude={longitude} />
      <div className="product_location_description">
        <h2 className="product_paddingtop">Qué tenés que saber</h2>
        <hr className="product_separador"></hr>
        <div className="product_politicas">
          {
            politics === null ? <p> No disponible</p> :
              politics.map((element) => {
                return (
                  <div
                    className="product_div_politicas_container"
                    key={element.id}
                  >
                    <h4 className="product_h4_politicas">{element.title}</h4>
                    <p className="product_p_politicas">{element.description}</p>
                  </div>
                );
              })
          }
        </div>
      </div>
    </>
  );
};
