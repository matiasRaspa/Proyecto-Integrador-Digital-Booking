import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { useFetch } from "../../hooks/useFetch";
import "../../styles/components/_booking.scss";
import Calendar from "./Calendar";
import { FaRegCheckCircle } from "react-icons/fa";
import { getPayloadJwt } from "../../helper/getPayloadJwt";
import { getDateFormat } from "../../helper/getDateFormat";
import { BarComeHome } from "../bar-come-home/BarComeHome";
import { getStarNum } from "../../helper/getStarNum";
import ModalPortal from "../modals/ModalPortal";
import { Success } from "../modals/success/Success";
import { UserContext } from '../../UserContext';



export const Booking = () => {

  const navigate = useNavigate();
  const { setAuth } = useContext(UserContext);
  const [success, setsuccess] = useState(false);

  const [reservationDate, setReservationDate] = useState({
    startDate: null,
    endDate: null,
  });

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const { id } = useParams();
  const [data] = useFetch(
    `http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/products/${id}`
  );

  const { startDate, endDate } = reservationDate;
  const starNum = data && data.stars;
  const token = localStorage.getItem("token");
  const payload = getPayloadJwt(token);
  const email = payload.sub;
  const nombreCompleto = payload.aud.split(" ");
  const nombre = nombreCompleto[0];
  const apellido = nombreCompleto[1];


  const checkIn = startDate === null ? "__/__/__" : getDateFormat.getFullDate(startDate);
  const checkOut = endDate === null ? "__/__/__" : getDateFormat.getFullDate(endDate);

  const handleSubmit = (e) => {

    e.preventDefault();

    const hasExpiredToken = payload === null ? false : new Date(payload.exp * 1000) >= new Date();

    if (!hasExpiredToken) {
      if (window.confirm('Su sesión ha terminado, ¿Desea inciar nuevamente sesión?')) {
        setAuth(false)
        setTimeout(() => navigate('/login'), 200)
      } else {
        setAuth(false)
        setTimeout(() => navigate('/'), 200)
      }
    } else {

      let data = {
        eta: document.getElementById("hour").value,
        startDate: startDate && getDateFormat.getFullDate(startDate),
        endDate: endDate && getDateFormat.getFullDate(endDate),
        user: { id: parseInt(payload.jti) },
        product: { id: parseInt(id) },
      };

      let requestOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": token,
        },
        body: JSON.stringify(data),
      }

      fetch("http://grupo6c1621back-env.eba-b34jmgbs.us-east-2.elasticbeanstalk.com/bookings", requestOptions)
        .then((res) => {
          if (res.status === 201 || res.status === 400) {
            setsuccess(true);
          }
        })
        .catch((err) => console.log(err));

    }

  }

  return (
    <div className="booking_fragment">
      <ModalPortal >
        <Success state={success} />
      </ModalPortal>
      <BarComeHome category={data && data.category.title} title={data && data.name} />
      <section>
        <form
          onSubmit={(e) => { handleSubmit(e) }}
          method="POST"
          name="booking"
          id="formularioBooking"
          className="booking_form"
        >
          <section className="booking_form_1">
            <section>
              <h2 className="booking_fechas">Completá tus datos</h2>
              <section className="booking_datos_usuario">
                <label className="booking_datos_usuario_label">
                  Nombre
                  <input
                    name="nombre"
                    className="booking_datos_usuario_input"
                    type="text"
                    id="nombre"
                    placeholder="Nombre"
                    value={nombre}
                    disabled
                  />
                </label>
                <label className="booking_datos_usuario_label">
                  Apellido
                  <input
                    name="apellido"
                    className="booking_datos_usuario_input"
                    type="text"
                    id="apellido"
                    placeholder="Apellido"
                    value={apellido}
                    disabled
                  />
                </label>
                <label className="booking_datos_usuario_label">
                  Correo electronico
                  <input
                    name="email"
                    className="booking_datos_usuario_input"
                    type="email"
                    id="email"
                    placeholder="Email"
                    value={email}
                    disabled
                  />
                </label>
                <label className="booking_datos_usuario_label">
                  Ciudad
                  <input
                    name="ciudad"
                    className="booking_datos_usuario_input"
                    type="text"
                    id="ciudad"
                    placeholder="Ciudad"
                  />
                </label>
              </section>
            </section>
            <section className="booking_calendar_container">
              <h2 className="product_fechas">Seleccioná tu fecha de reserva</h2>
              <section className="booking_seccion_calendar_container">
                <div className="product_div_calendar_container">
                  <Calendar
                    startDate={startDate}
                    endDate={endDate}
                    setReservationDate={setReservationDate}
                  />
                </div>
              </section>
            </section>
            <section>
              <h2 className="product_fechas">Tu horario de llegada</h2>
              <section className="booking_horarios_llegada">
                <h4 className="booking_h4_text">
                  {" "}
                  <FaRegCheckCircle /> Tu habitación estará lista para el
                  check-in entre las 10:00 AM y las 11:00 PM
                </h4>
                <label className="booking_horarios_label">
                  Indica tu horario estimado de llegada
                  <select
                    id="hour"
                    name="check_in"
                    className="booking_horarios_option"

                    required
                  >
                    <option value="" key="default" hidden>
                      Seleccionar hora de llegada
                    </option>
                    <option value={"10:00:00"} key={"10:00"}>
                      10:00 AM
                    </option>
                    <option value={"11:00:00"} key={"11:00"}>
                      11:00 AM
                    </option>
                    <option value={"12:00:00"} key={"12:00"}>
                      12:00 PM
                    </option>
                    <option value={"13:00:00"} key={"13:00"}>
                      01:00 PM
                    </option>
                    <option value={"14:00:00"} key={"14:00"}>
                      02:00 PM
                    </option>
                    <option value={"15:00:00"} key={"15:00"}>
                      03:00 PM
                    </option>
                    <option value={"16:00:00"} key={"16:00"}>
                      04:00 PM
                    </option>
                    <option value={"17:00:00"} key={"17:00"}>
                      05:00 PM
                    </option>
                    <option value={"18:00:00"} key={"18:00"}>
                      06:00 PM
                    </option>
                    <option value={"19:00:00"} key={"19:00"}>
                      07:00 PM
                    </option>
                    <option value={"20:00:00"} key={"20:00"}>
                      08:00 PM
                    </option>
                    <option value={"21:00:00"} key={"21:00"}>
                      09:00 PM
                    </option>
                    <option value={"22:00:00"} key={"22:00"}>
                      10:00 PM
                    </option>
                    <option value={"23:00:00"} key={"23:00"}>
                      11:00 PM
                    </option>
                  </select>
                </label>
              </section>
            </section>
          </section>
          <section className="booking_form_2">
            <section className="booking_resumen">
              <div className="booking_resumen_h2_padding">
                <h2 className="product_fechas">Detalle de la reserva</h2>
              </div>
              <div>
                <img
                  className="booking_resumen_imagen"
                  src={data && data.images[0].url}
                  alt="imagen"
                />
              </div>
              <div>
                <div className="booking_description">
                  <h4 className="booking_resumen_titulo">
                    {data && data.category.title}
                  </h4>
                  <h1 className="booking_resumen_nombre">
                    {data && data.name}
                  </h1>
                  <p className="booking_resumen_stars">{getStarNum(starNum)}</p>
                  <div>
                    <p className="booking_resumen_location">
                      <FontAwesomeIcon
                        className="product_location_icon"
                        icon={faLocationDot}
                      />
                      {data && data.location.fullName}
                    </p>
                  </div>
                </div>
                <hr className="booking_separador"></hr>
                <div className="booking_div_resumen_horarios">
                  <h2>Check in</h2>
                  <h3>{checkIn}</h3>
                </div>
                <hr className="booking_separador"></hr>
                <div className="booking_div_resumen_horarios">
                  <h2>Check out</h2>
                  <h3>{checkOut}</h3>
                </div>
                <hr className="booking_separador"></hr>
                <div className="booking_div_btn_container">
                  <button type="submit" className="product_btn_form">
                    Confirmar reserva
                  </button>
                </div>
              </div>
            </section>
          </section>
        </form>
      </section>
      <section>
        <div className="product_location_description">
          <h2 className="product_paddingtop">Qué tenés que saber</h2>
          <hr className="product_separador"></hr>
          <div className="product_politicas">
            {data &&
              data.politics.map((element) => {
                return (
                  <div
                    className="product_div_politicas_container"
                    key={element.id}
                  >
                    <h4 className="product_h4_politicas">{element.title}</h4>
                    <p className="product_p_politicas">{element.description}</p>
                  </div>
                );
              })}
          </div>
        </div>
      </section>
    </div>
  );
};
