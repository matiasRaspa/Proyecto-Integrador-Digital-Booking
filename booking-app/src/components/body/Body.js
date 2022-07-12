import React from 'react'
import { Category } from './category/Category'
import { Paginate } from './paginate/Paginate'


export const Body = () => {

    return (
        <main>
            <Category />
            <br />
            <Paginate itemsPerPage={8} />
        </main>
    )
}
