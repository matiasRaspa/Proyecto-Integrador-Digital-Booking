import React from 'react'
import { Link } from 'react-router-dom'

import arrowReturn from "../../img/arrow-return.png"

export const BarComeHome = ({ category, title }) => {
    return (
        <section className="product_seccion1">
            <div>
                <h4>{category}</h4>
                <h1>{title}</h1>
            </div>
            <div className="product_arrow">
                <Link to={"/"}>
                    <img src={arrowReturn} alt="arrow-return" />
                </Link>
            </div>
        </section>
    )
}
