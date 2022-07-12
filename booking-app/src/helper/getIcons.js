import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faWifi,
    faSwimmer,
    faMugSaucer,
    faPaw,
    faTv,
    faCreditCard,
    faCar,
    faSnowflake,
    faBroom,
    faKitchenSet,
    faSmoking,
    faBellConcierge,
    faSoap,
    faVanShuttle
} from '@fortawesome/free-solid-svg-icons';



export const getIcons = (element, style) => {
    return (
        element.type === "breakfast" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faMugSaucer}
                />
            </>
        ) : element.type === "pool" ? (
            <>
                <FontAwesomeIcon
                    key={element.id} 
                    className={style}
                    icon={faSwimmer}
                />
            </>
        ) : element.type === "kitchen" ? (
            <>
                <FontAwesomeIcon
                    key={element.id} 
                    className={style}
                    icon={faKitchenSet}
                />
            </>
        ) : element.type === "tv" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faTv} />
            </>
        ) : element.type === "pet" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faPaw} />
            </>
        ) : element.type === "ac" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faSnowflake}
                />
            </>
        ) : element.type === "parking" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faCar} />
            </>
        ) : element.type === "smoke" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faSmoking}
                />
            </>
        ) : element.type === "creaditcard" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faCreditCard}
                />
            </>
        ) : element.type === "housekeeping" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faBroom}
                />
            </>
        ) : element.type === "roomservice" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faBellConcierge}
                />
            </>
        ) : element.type === "transfer" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faVanShuttle}
                />
            </>
        ) : element.type === "laundry" ? (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faSoap}
                />
            </>
        ) : (
            <>
                <FontAwesomeIcon
                    key={element.id}
                    className={style}
                    icon={faWifi}
                />
            </>
        )


    )
}
