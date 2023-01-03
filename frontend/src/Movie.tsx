import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import MovieDetail from "../components/MovieDetail";
import { FaUser } from "react-icons/fa";
import { useEffect, useState } from "react";
import Movie from "../types/Movie";
import axios from "axios";

const Movie = () => {
  const params = useParams();
  const [movie, setMovie] = useState<Movie | null>(null);
  const [loading, setLoading] = useState("loading");
  useEffect(() => {
    async function getMovie() {
      const res = await axios.get(
        `http://localhost:8080/movies?type=search&movie=${params.name}`
      );
      if (res.status == 200) {
        setLoading("success");
        setMovie(res.data);
      } else {
        setLoading("failed");
      }
    }
    getMovie();
  }, []);

  const casts = movie?.cast.split(",");
  if (loading == "loading") {
    return <div>Loading...</div>;
  } else {
    return (
      <>
        {loading == "success" ? (
          <div>
            <Navbar />
            <MovieDetail movie={movie} />
            <div>
              <div className="md:max-w-6xl m-auto">
                <h1 className="text-3xl font-semibold p-10 lg:p-0">Director</h1>
                <div className="flex p-10">
                  {movie?.director.length !== 0 && (
                    <div className="p-5 w-fit">
                      <div className="bg-white w-fit rounded-full m-auto mb-5">
                        <FaUser className="text-black text-7xl p-2" />
                      </div>
                      <p className="text-xl">{movie?.director}</p>
                    </div>
                  )}
                </div>
                <h1 className="text-3xl font-semibold p-10 lg:p-0">Casts</h1>
                <div className="flex p-10">
                  {casts?.map((data) => (
                    <div className="p-5 pt-8 w-fit" key={data}>
                      <div className="bg-white w-fit rounded-full m-auto mb-5">
                        <FaUser className="text-black text-7xl p-2" />
                      </div>
                      <p className="text-xl">{data}</p>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div>Not found</div>
        )}
      </>
    );
  }
};

export default Movie;
