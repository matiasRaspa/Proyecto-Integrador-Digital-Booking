import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStar } from '@fortawesome/free-solid-svg-icons';

export const getStarNum = (num) => {

    let impresiones = []

    for (let i = 0; i < num; i++) {

        impresiones.push(<FontAwesomeIcon key={i.toString()} className="body__star" icon={faStar} />);
    }

    return impresiones;
}