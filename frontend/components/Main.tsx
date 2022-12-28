import Filter from "./Filter";
export default function Main() {
  return (
    <div className="block md:flex mt-10 justify-center px-10">
      <div className="max-w-sm w-full md:text-start text-center ">
        <h1 className="text-2xl font-semibold font-serif">Filters</h1>
        <Filter />
      </div>
      <div className="w-full max-w-3xl text-2xl font-semibold font-serif md:text-start text-center">
        <h1 className="text-2xl font-semibold font-serif mb-8">Movies</h1>
        <div className="grid grid-cols-34 grid-flow-rows gap-2">
          <div className="w-52 cursor-pointer">
            <img src="./movie.png" className="object-fill" alt="" />
            <div className="px-1">
              <h2 className="text-black text-xl truncate mt-4">
                Avatar: The Way of Water
              </h2>
              <p className="text-sm font-thin truncate">
                Action, Adventure, Fantasy, Sci-Fi
              </p>
              <p className="text-sm font-thin truncate">
                English, Kannada, Malayalam, Tamil, Telugu, Hindi
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
