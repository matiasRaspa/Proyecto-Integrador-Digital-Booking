import successCheck from "../../../img/success-check.png";
import { Link } from "react-router-dom";

export const SuccessAdmin = ({ state }) => {
  return (
    <>
      {state &&
        <div className="success_container">
          <div className="success_container_card">
            <img src={successCheck} alt="imagen-check" />
            <h3 className="success_h3_admin">Tu propiedad se ha creado con éxito</h3>
            <Link to={"/"}>
              <button className="success_btn">Volver</button>
            </Link>
          </div>
        </div>
      }
    </>
  );
};
