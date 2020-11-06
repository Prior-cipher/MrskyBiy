export default (
    [...Array(10)].map((_, Y) => {
        return [...Array(10)].map((_, X) => {
            return ({
                x: X+1,
                y: Y+1,
                containsShip: false,
                isShipVisible: false
            })
        })

    })
)