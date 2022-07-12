import successCheck from "../../img/success-check.png"
import { Link, useNavigate } from 'react-router-dom';

export const SuccessRegister = ({state}) => {
  const navigate = useNavigate();
  return (
    <>
      {state &&
        <div className="successRegister__container">
          <div className='successRegister__background-success' onClick={()=>{ navigate('/login')}}/>
          <div className="successRegister__container_card">
            <img src={successCheck} alt="imagen-check" />
            <h2 className="successRegister__h2">¡Muchas Gracias!</h2>
            <h3 className="successRegister__h3">Su registro se ha realizado con éxito</h3>
            <Link to={"/login"}>
              <button className="successRegister__btn">ok</button>
            </Link>
          </div>
        </div>
      }
    </>
  );
};
