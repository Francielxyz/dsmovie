import MovieCard from "components/MovieCard";
import Pagination from "components/Pagination";

function Listing() {
    return (
        <>
            <Pagination />

            <div className="container">
                <div className="row">
                    <div className="col-sm-6 col-xl-3 col-lg-4 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-xl-3 col-lg-4 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-xl-3 col-lg-4 mb-3">
                        <MovieCard />
                    </div>
                    <div className="col-sm-6 col-xl-3 col-lg-4 mb-3">
                        <MovieCard />
                    </div>
                </div>
            </div>
        </>
    )
}

export default Listing;
