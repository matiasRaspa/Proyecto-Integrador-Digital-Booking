import React from 'react'

export const AlertModal = ( {alert,alertType} ) => {
  const error = "Lamentablemente no ha podido registrarse. Por favor, intente más tarde";
  const success = "Regitro Exitoso";
  return (
    alert &&
        <div  className={ alertType === "success" ? 'modal-success' : 'modal-error'}>
          {alertType === "error" ? error : success }
        </div>
  )
}

