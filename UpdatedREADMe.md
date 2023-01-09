<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/github_username/repo_name">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Digital Nomad</h3>

  <p align="center">
    Digital Nomad is a service that provides customers with a user experience that makes 
    choosing where to travel easy. Customers are provided travel category options to choose 
from and then a curated list of travel destinations is displayed. Customers can select and 
save favorite destinations into DynamoDB. Once saved, Digital Nomad provides the customer 
with a unique URL that they can bookmark and return to view their saved list in the future.
    <br />
    <a href="https://github.com/github_username/repo_name"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/github_username/repo_name">View Demo</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues">Report Bug</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

### Context
This project was a mid-stone group project completed at the Nashville Software School. 
Our Cohort was split into teams of four and given four weeks to design, build, and finish our application. 
Following the SCRUM methodology we created our design and conducted two sprints.
We split responsibilities throughout the project. During the first sprint I worked on the front end design to create the basic
layout of our webpage, designed the first dropdown menu, buttons, and added logic to connect
the front and back end through our API. During the second sprint I moved to the back end and added functionality
to save and retrieve favorite destinations using DynamoDB. I also contributed in the writing of unit tests for our JAVA classes.

### What I learned
Through this project I learned how to connect the front and back ends through a custom API. I learned how easy it is
to over commit or under commit on sprint planning goals. Some features, especially in the beginning phases, took much longer
than we expected, while others were completed faster as my team gained experience. I learned how to collaborate and work as a team
on a project from start to finish. As a team worked through challenges and frustrations, shared moments of success, 
and finished strong together! 

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

* ![Java](https://badges.aleen42.com/src/java.svg)
* ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E.svg?style=for-the-badge&logo=JavaScript&logoColor=black)
* ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
* ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
* [![AWS CloudFormation][AWS CloudFormation]][CloudFormation-url]
* ![AWS DynamoDB](https://img.shields.io/badge/Amazon%20DynamoDB-4053D6?style=for-the-badge&logo=Amazon%20DynamoDB&logoColor=white)
* ![AWS Lambda](https://img.shields.io/badge/AWS%20Lambda-FF9900.svg?style=for-the-badge&logo=AWS-Lambda&logoColor=white)
* [![API Gateway][API Gateway]][Gateway-url]
* ![Amazon S3](https://img.shields.io/badge/Amazon%20S3-569A31.svg?style=for-the-badge&logo=Amazon-S3&logoColor=white)
* ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
* ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
* ![IntelliJ](https://badges.aleen42.com/src/idea.svg)
* ![VS Code](https://badges.aleen42.com/src/visual_studio_code.svg)
* ![Docker](https://badges.aleen42.com/src/docker.svg)



<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

Please follow the instructions below to set up a copy of Digital Nomad and run locally.


### Prerequisites

1. Create or use an existing Amazon AWS account.
2. Install the latest version of AWS CLI. [Link to documentation](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
3. Install the latest version of AWS SAM CLI. [Link to documentation](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html)
4. You may need to install NodeJS before you can run the `npm` commands below.

- On Windows / WSL:
```shell
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash - &&\
sudo apt-get install -y nodejs
```
- On macOS:
```shell
brew install node
```

### Instructions to Run Locally
1. Clone the repo:
   ```sh
   git clone https://github.com/winters5885/NSS-digital-nomad.git
   ```
2. Run the Lambda service (backend):
    - Build the Java code: `sam build`
    - Run the local API: `sam local start-api --warm-containers LAZY`
3. Run a local web server (frontend):
    - CD into the web directory: `cd web`
    - Install dependencies : `npm install`
    - Run the local server: `npm run run-local`

After doing all of this, you will have a server running on port `8000` - you can access it by going to [http://localhost:8000](http://localhost:8000) in your browser.


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Feature 1
- [ ] Feature 2
- [ ] Feature 3
    - [ ] Nested Feature

See the [open issues](https://github.com/github_username/repo_name/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Steve Winters - steve.winters85@gmail.com

Project Link: [https://github.com/winters5885/NSS-digital-nomad](https://github.com/winters5885/NSS-digital-nomad)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* []()
* []()
* []()

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com
[AWS CloudFormation]: https://img.shields.io/badge/AWS_CloudFormation-red?style=for-the-badge
[Cloudformation-url]: https://aws.amazon.com/cloudformation/
[API Gateway]: https://img.shields.io/badge/AWS_API_Gateway-black?style=for-the-badge&logo=amazonapigateway&logoColor=FF4F8B
[Gateway-url]: https://aws.amazon.com/api-gateway/