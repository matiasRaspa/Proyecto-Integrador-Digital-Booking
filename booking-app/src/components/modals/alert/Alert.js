import React from "react";
import AlertSVG from '../../../img/AlertSVG';
import { useLocation } from 'react-router-dom';


export default function Alert() {
  const location = useLocation();
  const showAlert = new URLSearchParams(location.search).get('showAlert');

  return (

    <div className={!showAlert ? "alert__hidden" : "alert__errorAlert"}>
      <AlertSVG />
      <h4 className="alert__txt">Para realizar una reserva necesitas estar logueado</h4>
    </div>

  );
}
