import successCheck from "../../../img/success-check.png"
import { Link, useNavigate } from 'react-router-dom';

export const Success = ({state}) => {
  const navigate = useNavigate();
  return (
    <>
      {state &&
        <div className="success_container">
          <div className='success__background-success' onClick={()=>{setTimeout(() => navigate('/'), 500)}}/>
          <div className="success_container_card">
            <img src={successCheck} alt="imagen-check" />
            <h2 className="success_h2">¡Muchas Gracias!</h2>
            <h3 className="success_h3">Su reserva se ha realizado con éxito</h3>
            <Link to={"/"}>
              <button className="success_btn">ok</button>
            </Link>
          </div>
        </div>
      }
    </>
  );
};
